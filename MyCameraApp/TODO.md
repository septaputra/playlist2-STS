# TODO List for Camera App Implementation

## 1. Update Dependencies
- [x] Add CameraX dependencies to app/build.gradle.kts (Core, Lifecycle, View, ImageCapture)

## 2. Update AndroidManifest.xml
- [x] Add CAMERA permission
- [x] Add camera hardware feature requirement

## 3. Create Permission Handling
- [x] Implement runtime permission request for CAMERA using rememberLauncherForActivityResult

## 4. Create CameraScreen Composable
- [x] Initialize variables: Context, LifecycleOwner, PreviewView, CameraSelector, Preview, ImageCapture
- [x] Implement CameraX binding logic using LaunchedEffect
- [x] Design UI with Box, AndroidView for PreviewView, and capture button
- [x] Implement capturePhoto function with file naming, metadata, and callbacks

## 5. Update MainActivity
- [x] Replace Greeting with CameraScreen or permission handling logic

## 6. Test and Verify
- [x] Build and run the app
- [ ] Test camera preview and photo capture
- [ ] Verify images are saved to gallery
