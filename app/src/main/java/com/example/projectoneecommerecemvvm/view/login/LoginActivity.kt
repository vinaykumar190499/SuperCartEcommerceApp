package com.example.projectoneecommerecemvvm.view.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.R
import com.example.projectoneecommerecemvvm.databinding.ActivityLoginBinding
import com.example.projectoneecommerecemvvm.model.data.UserLogin
import com.example.projectoneecommerecemvvm.model.data.UserRegisterInfo
import com.example.projectoneecommerecemvvm.viewmodel.LoginViewModel
import com.example.projectoneecommerecemvvm.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var  viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupObservers()
        setupEvents()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.error.observe(this) {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(it)
                .create()
                .show()
        }
        viewModel.loginResponseResult.observe(this) {
            viewModel.loginResponseResult.observe(this) {
                AlertDialog.Builder(this@LoginActivity)
                    .setTitle("Successfull!!!!")
                    .setMessage("Logged in Successfully")
                    .create()
                    .show()
            }
        }
    }
    private fun setupEvents() {
        with(binding){
            btnLogin.setOnClickListener{
                var emailStr = edtEmail.text.toString()
                var passStr = edtPass.text.toString()
                val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

                if(passStr.matches(passwordRegex.toRegex()) && emailStr.matches(emailRegex.toRegex())){
                    viewModel.userLogin(UserLogin(email_id=emailStr,password=passStr))
                }
                else{
//                    errMsg.text= getString(R.string.loginErrMsg)
                    val snackbar = Snackbar.make(binding.root, getString(R.string.loginErrMsg), Snackbar.LENGTH_INDEFINITE)
                    val snackbarView = snackbar.view
                    snackbar.setTextColor(
                        ContextCompat.getColor(this@LoginActivity,
                        R.color.errorMsgRed
                    )) // Change the message text color to red
                    snackbar.setActionTextColor(ContextCompat.getColor(this@LoginActivity,
                        R.color.snackbarActionBlue
                    )) // Change the action button text color to blue
                    snackbar.setAction("OK") {
                        // This will dismiss the Snackbar when clicked
                        edtEmail.text?.clear()
                        edtPass.text?.clear()
                    }
                    snackbar.show()
                }
            }
            noAccountTagd.setOnClickListener{
                val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(registerIntent)
            }
        }
    }
}