# Quest 3 Autostart

This is a prototype of an application that can autostart another app at device startup / boot.
It works by registering a receiver for the `BOOT_COMPLETED` event that first starts this app and then optionally another app.

## Development

Clone the repo, connect a quest 3 device, open the repo in Android Studio and click on "Run"

## Change the autostart app

Navigate to `app/src/main/java/com/lucajunge/quest3autostart/BootCompletedReceiver.kt` and change the `PACKAGE_TO_START` and `CLASS_TO_START` in line 13 and 14 respectively.

You can also change the `packageName` and`className` in the `MainActivity.kt` to also make the button in the UI start the corresponding app.

## (Optional) Enable Device Admin

1. Install the app on your device
2. Open the Account Settings page by issuing this command:

```bash
adb shell am start -n 'com.android.settings/.Settings$AccountDashboardActivity'
```

3. Navigate to "Passwords and Accounts"
4. Remove every account in the list
5. You can now close the settings
6. Enable Device Admin with the following command:

```bash
adb shell dpm set-device-owner com.lucajunge.quest3autostart/.DeviceOwnerReceiver
```
