package com.example.clonecoding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.clonecoding.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    private var googleSignInClient: GoogleSignInClient? = null
    private lateinit var binding: ActivityLoginBinding
    private lateinit var intentLaunch:  ActivityResultLauncher<Intent>
    private var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.emailLoginBtn.setOnClickListener { tryEmailLogin() }
        binding.googleSignInBtn.setOnClickListener { openGoogleForm() }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        Log.i(TAG, getString(R.string.web_client_id));
        googleSignInClient = GoogleSignIn.getClient(this, gso )

        intentLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d(TAG, result.toString());
            when (result.resultCode) {
                RESULT_OK -> {
                    var task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        var account = task.getResult(ApiException::class.java)!!
                        tryGoogleLogin(account)
                        Log.i(TAG, "tryGoogleLogin: " + account.id)
                    } catch (e: ApiException) {
                        Log.d(TAG, "Google sign in failed: " + e.message)
                    }
                }
            }
        }
    }

    private fun openGoogleForm() {
        var signInIntent = googleSignInClient?.signInIntent
        intentLaunch.launch(signInIntent)
    }

    private fun tryGoogleLogin(account: GoogleSignInAccount?) {
        auth?.signInWithCredential(
            GoogleAuthProvider.getCredential(account?.idToken, null)
        )?.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                moveMainPage(task.result.user)
            } else if(task.exception?.message.isNullOrEmpty()) {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, task.exception?.message.toString())
            } else {
                registryNewUser()
            }
        }
    }

    private fun tryEmailLogin() {
        Log.i(TAG, "sign in login button click")
        auth?.createUserWithEmailAndPassword(
            binding.emailText.text.toString(),
            binding.passwordText.text.toString()
        )?.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                moveMainPage(task.result.user)
            } else if(task.exception?.message.isNullOrEmpty()) {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, task.exception?.message.toString())
            } else {
              registryNewUser()
            }
        }
    }
    private fun registryNewUser() {
        auth?.createUserWithEmailAndPassword(
            binding.emailText.text.toString(),
            binding.passwordText.text.toString()
        )?.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                //
            } else {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, task.exception?.message.toString())
            }
        }
    }
    private fun moveMainPage (user: FirebaseUser?) {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}