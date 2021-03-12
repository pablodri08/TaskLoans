package com.xmartlabs.taskloans.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xmartlabs.taskloans.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme_NoActionBar)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
