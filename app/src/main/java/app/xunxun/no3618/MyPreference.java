package app.xunxun.no3618;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by fengdianxun on 16/9/26.
 */

public class MyPreference {
    private static final String KEY_IMEI = "KEY_IMEI";
    private static final String KEY_IMEI_ORIGIN = "KEY_IMEI_ORIGIN";

    private static final String KEY_ANDROID_ID = "KEY_ANDROID_ID";
    private static final String KEY_ANDROID_ID_ORIGIN = "KEY_ANDROID_ID_ORIGIN";

    private static final String KEY_MAC = "KEY_MAC";
    private static final String KEY_MAC_ORIGIN = "KEY_MAC_ORIGIN";

    private static final String KEY_IMSI = "KEY_IMSI";
    private static final String KEY_IMSI_ORIGIN = "KEY_IMSI_ORIGIN";

    private static final String KEY_PHONE_TYPE = "KEY_PHONE_TYPE";
    private static final String KEY_PHONE_TYPE_ORIGIN = "KEY_PHONE_TYPE_ORIGIN";

    private static final String KEY_WIDTH = "KEY_WIDTH";
    private static final String KEY_WIDTH_ORIGIN = "KEY_WIDTH_ORIGIN";

    private static final String KEY_HEIGHT= "KEY_HEIGHT";
    private static final String KEY_HEIGHT_ORIGIN = "KEY_HEIGHT_ORIGIN";


    public static void saveImei(Context context, String imei) {
        save(context, KEY_IMEI, imei);
    }
    public static String getImei() {
        return get(KEY_IMEI, getOriginImei());
    }
    public static String getImei(Context context) {
        return get(context,KEY_IMEI,getOriginImei(context));
    }
    public static void saveOriginImei(Context context) {
        TelephonyManager telecomManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        save(context,KEY_IMEI_ORIGIN,telecomManager.getDeviceId());
    }
    public static String getOriginImei(Context context) {
        return get(context,KEY_IMEI_ORIGIN,"");
    }
    public static String getOriginImei() {
        return get(KEY_IMEI_ORIGIN, "");
    }



    public static void saveAndroidId(Context context, String androidId) {
        save(context,KEY_ANDROID_ID,androidId);
    }
    public static String getAndroidId(Context context){
        return get(context,KEY_ANDROID_ID,getOriginAndroidId(context));
    }
    public static String getAndroidId(){
        return get(KEY_ANDROID_ID,getOriginAndroidId());
    }
    public static void saveOriginAndroidId(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        save(context,KEY_ANDROID_ID_ORIGIN,android_id);
    }
    public static String getOriginAndroidId(Context context){
        return get(context,KEY_ANDROID_ID_ORIGIN,"");
    }
    public static String getOriginAndroidId(){
        return get(KEY_ANDROID_ID_ORIGIN,"");
    }



    public static void saveMac(Context context, String mac) {
        save(context,KEY_MAC,mac);
    }
    public static String getMac(Context context){
        return get(context,KEY_MAC,getOriginMac(context));
    }
    public static String getMac(){
        return get(KEY_MAC,getOriginMac());
    }
    public static void savetOriginMac(Context context) {
        WifiManager wifiMng = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfor = wifiMng.getConnectionInfo();
        String mac = wifiInfor.getMacAddress();
        save(context,KEY_MAC_ORIGIN,mac);
    }
    public static String getOriginMac(Context context){
        return get(context,KEY_MAC_ORIGIN,"");
    }
    public static String getOriginMac(){
        return get(KEY_MAC_ORIGIN,"");
    }





    public static void saveImsi(Context context, String imsi) {
        save(context,KEY_IMSI,imsi);
    }
    public static String getImsi(Context context){
        return get(context,KEY_IMSI,getOriginImsi(context));
    }
    public static String getImsi(){
        return get(KEY_IMSI,getOriginImsi());
    }
    public static void savetOriginImsi(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = telephonyManager.getSubscriberId();
        save(context,KEY_IMSI_ORIGIN,imsi);
    }
    public static String getOriginImsi(Context context){
        return get(context,KEY_IMSI_ORIGIN,"");
    }
    public static String getOriginImsi(){
        return get(KEY_IMSI_ORIGIN,"");
    }





    public static void savePhoneType(Context context, String phoneType) {
        save(context,KEY_PHONE_TYPE,phoneType);
    }
    public static String getPhoneType(Context context){
        return get(context,KEY_PHONE_TYPE,getOriginPhoneType(context));
    }
    public static String getPhoneType(){
        return get(KEY_PHONE_TYPE,getOriginPhoneType());
    }
    public static void saveOriginPhoneType(Context context) {
        String type = android.os.Build.MODEL;
        save(context,KEY_PHONE_TYPE_ORIGIN,type);
    }
    public static String getOriginPhoneType(Context context){
        return get(context,KEY_PHONE_TYPE_ORIGIN,"");
    }
    public static String getOriginPhoneType(){
        return get(KEY_PHONE_TYPE_ORIGIN,"");
    }





    public static void saveWidth(Context context, int width) {
        save(context,KEY_WIDTH,width);
    }
    public static int getWidth(Context context){
        return get(context,KEY_WIDTH,getOriginWidth(context));
    }
    public static int getWidth(){
        return get(KEY_WIDTH,getOriginWidth());
    }
    public static void saveOriginWidth(Context context) {
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        save(context,KEY_WIDTH_ORIGIN,w_screen);
    }
    public static int getOriginWidth(Context context){
        return get(context,KEY_WIDTH_ORIGIN,0);
    }
    public static int getOriginWidth(){
        return get(KEY_WIDTH_ORIGIN,0);
    }





    public static void saveHeight(Context context, int height) {
        save(context,KEY_HEIGHT,height);
    }
    public static int getHeight(Context context){
        return get(context,KEY_HEIGHT,getOriginHeight(context));
    }
    public static int getHeight(){
        return get(KEY_HEIGHT,getOriginHeight());
    }
    public static void saveOriginHeight(Context context) {
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int h_screen = dm.heightPixels;
        save(context,KEY_HEIGHT_ORIGIN,h_screen);
    }
    public static int getOriginHeight(Context context){
        return get(context,KEY_HEIGHT_ORIGIN,0);
    }
    public static int getOriginHeight(){
        return get(KEY_HEIGHT_ORIGIN,0);
    }



    private static void save(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_WORLD_READABLE);
        sharedPreferences.edit().putString(key, value).apply();
    }
    private static String get(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_WORLD_READABLE);
        return sharedPreferences.getString(key, defaultValue);
    }
    private static String get(String key, String defaultValue) {
        XSharedPreferences sharedPreferences = new XSharedPreferences("app.xunxun.no3618", "prefs");
        return sharedPreferences.getString(key, defaultValue);
    }


    private static void save(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_WORLD_READABLE);
        sharedPreferences.edit().putInt(key, value).apply();
    }
    private static int get(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_WORLD_READABLE);
        return sharedPreferences.getInt(key, defaultValue);
    }
    private static int get(String key, int defaultValue) {
        XSharedPreferences sharedPreferences = new XSharedPreferences("app.xunxun.no3618", "prefs");
        return sharedPreferences.getInt(key, defaultValue);
    }
}
