package com.example.clonecoding.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.clonecoding.databinding.ActivityAddPhotoBinding
import com.example.clonecoding.models.ContentDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    private var TAG = "AddPhotoActivity"

    private lateinit var binding: ActivityAddPhotoBinding
    private lateinit var intentLaunch: ActivityResultLauncher<Intent>
    var storage: FirebaseStorage? = null
    var store: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        store = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"

        intentLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d(TAG, result.toString());
            when (result.resultCode) {
                RESULT_OK -> {
                    photoUri = result.data?.data
                    binding.addPhotoImage.setImageURI(photoUri)
                }
                else -> finish()
            }
        }

        intentLaunch.launch(photoPickerIntent)
        binding.addPhotoBtnUpload.setOnClickListener { contentUpload() }
    }

    private fun contentUpload() {
        var timestamp = SimpleDateFormat("yyyyMMddd_HHmmss").format(Date())
        var imageName = "IMAGE_" + timestamp + "_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageName)

        storageRef?.putFile(photoUri!!)?.continueWithTask { taskId: Task<UploadTask.TaskSnapshot> ->
            return@continueWithTask storageRef.downloadUrl
        }?.addOnSuccessListener { uri ->
            store?.collection("images")?.document()?.set(
                ContentDTO(
                binding.addPhotoEditExplain.text.toString(),
                uri.toString(),
                auth?.currentUser?.uid,
                auth?.currentUser?.email,
                System.currentTimeMillis(),
                0
            )
            )
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}