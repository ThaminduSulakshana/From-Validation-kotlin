package com.example.lab3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.lab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var binding: ActivityMainBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val edtName: EditText = binding.edtName
        val edtEmail: EditText = binding.edtEmail
        val edtPhone: EditText = binding.edtPhone
        val edtPassword: EditText = binding.edtPassword
        val edtRePassword: EditText = binding.edtRePassword
        val btnSubmit: Button = binding.btnSubmit
        val btnCancel: Button = binding.btnCancel

        btnSubmit.setOnClickListener {
            showAlertBox(
                this,
                edtName.text.toString(),
                edtEmail.text.toString(),
                edtPhone.text.toString(),
                edtPassword.text.toString(),
                edtRePassword.text.toString()
            )
        }
        btnCancel.setOnClickListener{
            edtName.setText("")
            edtEmail.setText("")
            edtPhone.setText("")
            edtPassword.setText("")
            edtRePassword.setText("")
        }



    }

    fun showAlertBox(
        context: Context,
        name: String,
        email: String,
        phone: String,
        password: String,
        rePassword: String
    ) {
        val builder = AlertDialog.Builder(context)
        val message = "Email: $email\n" +
                "Phone: $phone\n" +
                "Passwords: ${if (password == rePassword) "Matching" else "NotMatching"}."
        builder.setTitle("Welcome $name!")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { _, _ ->
            Toast.makeText(context, "Submitted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Cancel") { _, _ ->
        }
        val dialog = builder.create()
        dialog.show()
    }
}

