package com.github.dan.NoStorageRestrict;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class DocumentHideBypass {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        try {
            XposedHelpers.findAndHookMethod(
                    "com.android.externalstorage.ExternalStorageProvider",
                    lpparam.classLoader,
                    "shouldHideDocument", java.lang.String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(false);
                        }
                    });
        } catch (Throwable e) {
            XposedBridge.log("Error hooking shouldHideDocument: " + e.getMessage());
        }
    }
}
