package com.phlexile.connectandoirdq;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thanosfisherman.wifiutils.TypeEnum;
import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionSuccessListener;
import com.thanosfisherman.wifiutils.wifiRemove.RemoveErrorCode;
import com.thanosfisherman.wifiutils.wifiRemove.RemoveSuccessListener;
import static com.thanosfisherman.wifiutils.WifiUtils.wifiLog;
import java.util.ArrayList;
import java.util.List;
import com.reactlibrary.rnwifi.RNWifiModule;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
    public void sendMessage(View view) {
//        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
       WifiUtils.withContext(this)
               .connectWith("FPT", "")
               .setTimeout(15000)
               .onConnectionResult( new ConnectionSuccessListener() {
               @Override
               public void success() {
                   Toast.makeText(MainActivity.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void failed(@NonNull ConnectionErrorCode errorCode) {
                   Toast.makeText(MainActivity.this, "EPIC FAIL!" + errorCode.toString(), Toast.LENGTH_SHORT).show();
               }
       })
           .start();
        // RNWifiModule.loadWifiList();
        }
}