package com.example.iaia.sampleandroidapplication.camera

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.graphics.Matrix
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.*
import android.widget.ImageView
import androidx.camera.core.CameraX.bindToLifecycle
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.FragmentCameraBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File

@RuntimePermissions
class CameraFragment : Fragment() {
    companion object {
        fun newInstance() = CameraFragment()
    }

    private val model: CameraViewModel by viewModel()
    private lateinit var binding: FragmentCameraBinding
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private lateinit var viewFinder: TextureView
    private lateinit var displayManager: DisplayManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false)

        binding.viewModel = model
        viewFinder = binding.viewFinder
        displayManager = viewFinder.context
            .getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        model.command.observe(this, Observer {
            when (it) {
                Command.Capture -> capture()
            }
        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        displayManager.registerDisplayListener(displayListener, null)
        showCameraWithPermissionCheck()
    }

    override fun onDetach() {
        super.onDetach()
        displayManager.unregisterDisplayListener(displayListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
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
        bindToLifecycle(this, preview, imageCapture)
    }

    private fun capture() {
        val file = File(
            requireActivity().externalMediaDirs.first(),
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
        val imageView = ImageView(requireContext()).apply {
            setImageURI(file.toUri())
        }
        AlertDialog.Builder(requireContext()).apply {
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
