package com.example.projectoneecommerecemvvm.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.R
import com.example.projectoneecommerecemvvm.databinding.ActivityRegisterBinding
import com.example.projectoneecommerecemvvm.model.data.UserRegisterInfo
import com.example.projectoneecommerecemvvm.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding
    lateinit var  viewModel:RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupObservers()
        setupEvents()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.error.observe(this) {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(it)
                .create()

        }
        viewModel.responseResult.observe(this) {
            binding.errMsgRegister.text="Registration Successfull!!!!"
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private fun setupEvents() {
        with(binding){
            btnRegister.setOnClickListener{
                var fullNameStr = edtfullNameRegister.text.toString()
                var mobileNoStr = edtMobileNumberRegister.text.toString()
                var emailIdStr = edtEmailIdRegister.text.toString()
                var passwordStr = edtPasswordRegister.text.toString()

                val fullNameRegex = "^[a-zA-Z\\s'-]+$"
                val mobileNoRegex = "^\\d{10}$"
                val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

                if (fullNameStr.matches(fullNameRegex.toRegex()) &&
                    mobileNoStr.matches(mobileNoRegex.toRegex()) &&
                    emailIdStr.matches(emailRegex.toRegex()) &&
                    passwordStr.matches(passwordRegex.toRegex())) {

                   viewModel.registerUser(UserRegisterInfo(fullNameStr,mobileNoStr,emailIdStr,passwordStr))
                } else {
                    errMsgRegister.text = getString(R.string.registrationErrMsg)
                }
            }
        }
    }
}