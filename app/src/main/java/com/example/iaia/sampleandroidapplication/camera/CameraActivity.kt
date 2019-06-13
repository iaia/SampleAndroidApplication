package com.example.iaia.sampleandroidapplication.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.databinding.DataBindingUtil
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityCameraBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class CameraActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, CameraActivity::class.java)
    }

    private val model: CameraViewModel by viewModel()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCameraBinding>(
            this,
            R.layout.activity_camera
        )
    }
    private val viewFinder by lazy { binding.viewFinder }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = model
        initToolbar()
    }

    override fun onStart() {
        super.onStart()
        showCameraWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
        viewFinder.post {
            startCamera()
        }
    }

    // @OnShowRationale(Manifest.permission.CAMERA)
    // fun onRationaleForCamera(requrest: PermissionRequest) { }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
    }

    // @OnNeverAskAgain(Manifest.permission.CAMERA)
    // fun onCameraNeverAskAgain() { }

    private fun startCamera() {
        val viewFinderSize = Size(viewFinder.width, viewFinder.height)
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setTargetResolution(viewFinderSize)
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        CameraX.bindToLifecycle(this, preview)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        val rotationDegrees = when (viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        viewFinder.setTransform(matrix)
    }
}
