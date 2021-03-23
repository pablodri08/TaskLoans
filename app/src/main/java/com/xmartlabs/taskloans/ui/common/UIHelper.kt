package com.xmartlabs.taskloans.ui.common

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

object UIHelper {
  fun validateMail(mail: String) = Patterns.EMAIL_ADDRESS.matcher(mail).matches()

  fun validatePassword(password: String) = password.isNotEmpty()

  fun showTextFieldError(validator: Boolean, textField: TextInputLayout, message: String) {
    if (validator) {
      textField.error = null
    } else {
      textField.error = message
    }
  }
}
