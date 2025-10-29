package com.example.mycameraapp

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.File

@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasAllPermissions by remember { mutableStateOf(false) }

    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsResult ->
        hasAllPermissions = permissionsResult.values.all { it }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(permissions)
    }

    if (hasAllPermissions) {
        CameraPreview(context, lifecycleOwner)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { permissionLauncher.launch(permissions) }) {
                Text("Request Permissions")
            }
        }
    }
}

@Composable
fun CameraPreview(context: Context, lifecycleOwner: LifecycleOwner) {
    val previewView = remember { PreviewView(context) }
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }
    val preview = remember { Preview.Builder().build() }
    val imageCapture = remember { ImageCapture.Builder().build() }

    LaunchedEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }, ContextCompat.getMainExecutor(context))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )

        // Tombol kamera di bagian bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            IconButton(
                onClick = { capturePhoto(context, imageCapture) },
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, CircleShape)
                    .padding(6.dp)
                    .background(Color.Red, CircleShape)
            ) { }
        }
    }
}

private fun capturePhoto(context: Context, imageCapture: ImageCapture) {
    val fileName = "MyCamera_${System.currentTimeMillis()}.jpg"

    val outputOptions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // ✅ Android 10+ (gunakan MediaStore)
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyCameraImages")
        }

        ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()
    } else {
        // ✅ Android 9 ke bawah (gunakan File manual)
        val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val photoFile = File(picturesDir, fileName)
        ImageCapture.OutputFileOptions.Builder(photoFile).build()
    }

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(context, "📸 Foto tersimpan di Galeri!", Toast.LENGTH_SHORT).show()

                // 🔁 Tambahan untuk Android 9 ke bawah agar foto langsung muncul di galeri
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    val photoFile = File(picturesDir, fileName)
                    val uri = Uri.fromFile(photoFile)
                    context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context, "❌ Gagal menyimpan: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    )
}
