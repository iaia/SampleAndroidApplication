package com.example.iaia.sampleandroidapplication.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityCameraBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File

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
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private lateinit var viewFinder: TextureView
    private lateinit var displayManager: DisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = model
        viewFinder = binding.viewFinder
        displayManager = viewFinder.context
            .getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        displayManager.registerDisplayListener(displayListener, null)
        model.command.observe(this, Observer {
            when (it) {
                Command.Capture -> capture()
            }
        })
        initToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        displayManager.unregisterDisplayListener(displayListener)
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

    /*
    @OnShowRationale(Manifest.permission.CAMERA)
    fun onRationaleForCamera(requrest: PermissionRequest) { }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() { }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() { }
    */

    @NeedsPermission(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    fun showCamera() {
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
        viewFinder.post {
            startCamera()
        }
    }

    private fun startCamera() {
        val metrics = DisplayMetrics().also {
            viewFinder.display.getRealMetrics(it)
        }
        val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)
        val screenSize = Size(metrics.widthPixels, metrics.heightPixels)

        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(screenAspectRatio)
            setTargetResolution(screenSize)
            setTargetRotation(viewFinder.display.rotation)
        }.build()

        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(screenAspectRatio)
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            setTargetRotation(viewFinder.display.rotation)
        }.build()

        preview = Preview(previewConfig)

        preview?.setOnPreviewOutputUpdateListener {
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        imageCapture = ImageCapture(imageCaptureConfig)
        CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun capture() {
        val file = File(
            externalMediaDirs.first(),
            "${System.currentTimeMillis()}.jpg"
        )
        imageCapture?.takePicture(
            file,
            object : ImageCapture.OnImageSavedListener {
                override fun onError(
                    error: ImageCapture.UseCaseError,
                    message: String, exc: Throwable?
                ) {
                    val msg = "Photo capture failed: $message"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.e("CameraXApp", msg)
                    exc?.printStackTrace()
                }

                override fun onImageSaved(file: File) {
                    previewSavedImage(file)
                }
            }
        )
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = (viewFinder.width) / 2f
        val centerY = (viewFinder.height) / 2f

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

    private fun previewSavedImage(file: File) {
        val imageView = ImageView(this).apply {
            setImageURI(file.toUri())
        }
        AlertDialog.Builder(this).apply {
            setTitle("Preview")
            setView(imageView)
            create()
            show()
        }
    }

    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit
        override fun onDisplayRemoved(displayId: Int) = Unit
        override fun onDisplayChanged(displayId: Int) = viewFinder.let { view ->
            preview?.setTargetRotation(view.display.rotation)
            imageCapture?.setTargetRotation(view.display.rotation)
        } ?: Unit
    }
}
