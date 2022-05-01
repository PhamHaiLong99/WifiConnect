wifiUtils.withContext() ->
mContext = context;
mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
if (mWifiManager == null) {
    throw new RuntimeException("WifiManager is not supposed to be null");
}
mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
mWifiStateReceiver = new WifiStateReceiver(mWifiStateCallback);
mWifiScanReceiver = new WifiScanReceiver(mWifiScanResultsCallback);
mHandler = new WeakHandler();
mWifiConnectionReceiver = new WifiConnectionReceiver(mWifiConnectionCallback, mWifiManager);
mTimeoutHandler = new TimeoutHandler(mWifiManager, mHandler, mWifiConnectionCallback);
                                

WifiScanReceiver.onReceive()->(WifiScanCallback)callback.onScanResultsReady->onScanResultsReady->connectToWifi->connectAndroidQ(dang viet).

wifiUtils.connectWith() -> connectWith -> 
public WifiSuccessListener connectWith(@NonNull final String ssid, @NonNull final String password);



wifiUtils.start() -> enableWifi ->
mWifiStateListener = wifiStateListener;
if (mWifiManager.isWifiEnabled()) {
    mWifiStateCallback.onWifiEnabled();
} else {
    if (mWifiManager.setWifiEnabled(true)) {
        registerReceiver(mContext, mWifiStateReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    } else {
        of(wifiStateListener).ifPresent(stateListener -> stateListener.isSuccess(false));
        of(mScanResultsListener).ifPresent(resultsListener -> resultsListener.onScanResults(new ArrayList<>()));
        of(mConnectionWpsListener).ifPresent(wpsListener -> wpsListener.isSuccessful(false));
        mWifiConnectionCallback.errorConnect(ConnectionErrorCode.COULD_NOT_ENABLE_WIFI);
        wifiLog("COULDN'T ENABLE WIFI");
    }
};

wifiUtils.connectionSuccess -> ConnectionSuccessListener