# TODO Fixes for Camera App

## 1. Functional Fix: Storage Permissions and Error Handling
- [x] Add WRITE_EXTERNAL_STORAGE and READ_EXTERNAL_STORAGE permissions to AndroidManifest.xml
- [x] Update CameraScreen.kt to request multiple permissions (CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE) using RequestMultiplePermissions
- [x] Ensure CameraScreen only shows if all permissions are granted
- [x] Improve error handling in capturePhoto function

## 2. UI Adjustment: Move Capture Button Position
- [x] Adjust padding on the Box containing the IconButton to move capture button up slightly (increase bottom padding)

## 3. Test and Verify
- [x] Build the app
- [ ] Verify permissions and UI changes
