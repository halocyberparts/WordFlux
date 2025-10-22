package com.example.wordflux_v11

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.wordflux_v11.databinding.ActivityPermissionManagerBinding

class PermissionManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionManagerBinding

    private val overlayPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        updateUi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOverlay.setOnClickListener { requestOverlayPermission() }
        binding.btnContinue.setOnClickListener {
            if (canDrawOverlays()) {
                Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show()
                // startActivity(Intent(this, MainActivity::class.java))
                // finish()
            } else {
                Toast.makeText(this, "Overlay permission required.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    private fun updateUi() {
        val granted = canDrawOverlays()
        binding.btnOverlay.isEnabled = !granted
        binding.btnContinue.isEnabled = granted
        binding.statusText.text = if (granted)
            "Overlay permission granted"
        else
            "Overlay permission not granted"
    }

    private fun requestOverlayPermission() {
        if (!canDrawOverlays()) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            overlayPermissionLauncher.launch(intent)
        } else {
            Toast.makeText(this, "Already granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun canDrawOverlays(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else true
    }
}











//package com.example.wordflux_v11
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.provider.Settings
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import com.example.wordflux_v11.databinding.ActivityPermissionManagerBinding // ✅ Must match your layout file name
//
//class PermissionManagerActivity : AppCompatActivity() {
//
//    // ✅ ViewBinding reference to your layout
//    private lateinit var binding: ActivityPermissionManagerBinding
//
//    private var hasOverlayPermission = false
//
//    // ✅ ActivityResult launcher for overlay permission
//    private val overlayPermissionLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) {
//        if (canDrawOverlays()) {
//            hasOverlayPermission = true
//            Log.d("PermissionManager", "Overlay permission GRANTED.")
//            updateUi()
//        } else {
//            Log.d("PermissionManager", "Overlay permission DENIED.")
//            Toast.makeText(this, "Overlay permission is required to continue", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // ✅ Inflate the layout
//        binding = ActivityPermissionManagerBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupClickListeners()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        checkInitialPermissions()
//        updateUi()
//    }
//
//    private fun checkInitialPermissions() {
//        hasOverlayPermission = canDrawOverlays()
//    }
//
//    private fun setupClickListeners() {
//        binding.btnOverlay.setOnClickListener {
//            requestOverlayPermission()
//        }
//
//        binding.btnContinue.setOnClickListener {
//            if (hasOverlayPermission) {
//                Toast.makeText(this, "Permissions granted! Navigating to main app...", Toast.LENGTH_SHORT).show()
//                // Example: start next activity
//                // startActivity(Intent(this, MainActivity::class.java))
//                // finish()
//            }
//        }
//    }
//
//    private fun requestOverlayPermission() {
//        if (!canDrawOverlays()) {
//            val intent = Intent(
//                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                Uri.parse("package:$packageName")
//            )
//            overlayPermissionLauncher.launch(intent)
//        } else {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun canDrawOverlays(): Boolean {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Settings.canDrawOverlays(this)
//        } else true
//    }
//
//    private fun updateUi() {
//        if (hasOverlayPermission) {
//            binding.btnOverlay.text = "Permission Granted"
//            binding.btnOverlay.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
//            binding.btnOverlay.isEnabled = false
//            binding.btnContinue.isEnabled = true
//        } else {
//            binding.btnOverlay.text = "Grant Overlay Permission"
//            binding.btnOverlay.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
//            binding.btnOverlay.isEnabled = true
//            binding.btnContinue.isEnabled = false
//        }
//    }
//}
