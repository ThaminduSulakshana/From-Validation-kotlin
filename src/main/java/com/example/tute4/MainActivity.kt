package com.example.tute4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.tute4.models.ValidationResult.FormData
import com.example.tute4.models.ValidationResult.ValidationResult

class MainActivity : AppCompatActivity() {
    lateinit var edtStudentId: EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester: Spinner
    lateinit var cbAgree: CheckBox
    lateinit var tvYear: TextView
    lateinit var tvSemester: TextView
    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentId = findViewById(R.id.edtStudentID)
        tvYear = findViewById(R.id.tvYears)
        spnYear = findViewById(R.id.spinyear)
        tvSemester = findViewById(R.id.tvSemester)
        spnSemester = findViewById(R.id.spinsem)
        cbAgree = findViewById(R.id.cbAgreement)
    }

    fun displayAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which->}
        val dialog = builder.create()
        dialog.show()
    }

    fun submit(v: View) {
        val myForm = FormData(
            edtStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )
        val studentIdValidation = myForm.validateStudentId()
        val spnYearValidation = myForm.validateYear()
        val spnSemesterValidation = myForm.validateSemester()
        val cbAgreeValidation = myForm.validateAgreement()
        when (studentIdValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                edtStudentId.error = studentIdValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                edtStudentId.error = studentIdValidation.errorMessage
            }
        }
        when (spnYearValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                val tv: TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = spnYearValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                val tv: TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = spnYearValidation.errorMessage
            }
        }
        when (spnSemesterValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                val tv: TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = spnSemesterValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                val tv: TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = spnSemesterValidation.errorMessage
            }
        }
        when (cbAgreeValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                displayAlert("Error", cbAgreeValidation.errorMessage)
            }
            is ValidationResult.Empty -> {
            }
        }
        if (count == 4) {
            displayAlert("Success", "You have successfully registered")
        }
    }
}
