package app.xunxun.no3618;

import android.content.ContentResolver;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * 核心类.
 * Created by fengdianxun on 16/9/23.
 */

public class Core implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);
        hookImei(loadPackageParam);
        hookAndroidId(loadPackageParam);
        hookMac(loadPackageParam);
        hookImsi(loadPackageParam);
        hookPhoneType();
        hookWidthHeight(loadPackageParam);


    }
    private void hookWidthHeight(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.content.res.Resources", loadPackageParam.classLoader,
                "getDisplayMetrics", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        DisplayMetrics dm = (DisplayMetrics) param.getResult();
                        dm.widthPixels = MyPreference.getWidth();
                        dm.heightPixels = MyPreference.getHeight();
                        param.setResult(dm);
                    }
                });
    }

    private void hookImei(XC_LoadPackage.LoadPackageParam lpparam) {
        if (Build.VERSION.SDK_INT < 22) {
            try {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", lpparam.classLoader,
                        "getDeviceId", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                DisplayMetrics dm = (DisplayMetrics) param.getResult();
//                                dm.widthPixels =
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm.GSMPhone", lpparam.classLoader,
                        "getDeviceId", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getDeviceId", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT == 22) {
            try {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", lpparam
                        .classLoader, "getDeviceId", new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.setResult(MyPreference.getImei());
                    }
                });
            } catch (Exception v0) {
                v0.printStackTrace();
            }

            try {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneProxy", lpparam
                        .classLoader, "getDeviceId", new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.setResult(MyPreference.getImei());
                    }
                });
            } catch (Exception v0) {
                v0.printStackTrace();
            }

            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getDeviceId", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception v0) {
                v0.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getDeviceId", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception v0) {
                v0.printStackTrace();
            }

            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getImei", new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception v0) {
                v0.printStackTrace();
            }

            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getDeviceId", Integer.TYPE, new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception v0) {
                v0.printStackTrace();
            }

            try {
                XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                        "getImei", Integer.TYPE, new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(MyPreference.getImei());
                            }
                        });
            } catch (Exception v0) {
                v0.printStackTrace();
            }
        }

    }
    private void hookAndroidId(XC_LoadPackage.LoadPackageParam lpparam){

        try {
            XposedHelpers.findAndHookMethod(Settings.Secure.class.getName(), lpparam.classLoader, "getString", ContentResolver.class, String.class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    if (param.args[1] == Settings.Secure.ANDROID_ID) {
                        param.setResult(MyPreference.getAndroidId());
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hookMac(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        try {
            XposedHelpers.findAndHookMethod(WifiInfo.class.getName(), loadPackageParam.classLoader, "getMacAddress", new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(MyPreference.getMac());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hookImsi(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        try {
            XposedHelpers.findAndHookMethod(TelephonyManager.class.getName(), loadPackageParam.classLoader, "getSubscriberId", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(MyPreference.getImsi());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hookPhoneType() throws NoSuchFieldException, IllegalAccessException {
        try {
            //hook static variable with reflect
            Field model = Build.class.getDeclaredField("MODEL");
            model.setAccessible(true);
            model.set(Build.class, MyPreference.getPhoneType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}
