package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.fdsoft.resourcechecker.R;

import java.util.Locale;

public class AppPackage {

    private String packageName;
    private String locale;
    private String appName;
    private String localizedAppName;
    private String versionName;
    private int versionCode;

    private Context mContext;

    public AppPackage(Context context, String packageName, String locale) {
        mContext = context;
        this.locale = locale;
        this.packageName = packageName;

        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi;


        try {
            pi = pm.getPackageInfo(this.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            pi = null;
            e.printStackTrace();
        }

        if (pi != null) {
            this.appName = pi.applicationInfo.loadLabel(pm).toString();
            this.versionName = pi.versionName;
            this.versionCode = pi.versionCode;

            if (TextUtils.isEmpty(locale)) {
                localizedAppName = null;
            } else {
                Resources resources = getResourceFromPackage(packageName);

                try {
                    if (resources == null) {
                        localizedAppName = null;
                    } else {
                        if (!TextUtils.isEmpty(locale)) {
                            Configuration configuration = resources.getConfiguration();

                            String[] splitLocale = null;
                            if (locale.contains("-") || locale.contains("_")) {
                                splitLocale = locale.split("-|_");

                                if (splitLocale.length == 2) {
                                    configuration.setLocale(new Locale(splitLocale[0],
                                            splitLocale[1].length() == 2
                                                    ? splitLocale[1]
                                                    : splitLocale[1].substring(1, splitLocale[1].length())));
                                } else if (splitLocale.length == 3) {
                                    configuration.setLocale(new Locale(splitLocale[0],
                                            splitLocale[1].length() == 2
                                                    ? splitLocale[1]
                                                    : splitLocale[1].substring(1, splitLocale[1].length()),
                                            splitLocale[2]));
                                } else {
                                    configuration.setLocale(new Locale(locale));
                                }
                            } else {
                                configuration.setLocale(new Locale(locale));
                            }

                            resources.updateConfiguration(configuration, null);
                            localizedAppName = resources.getString(pi.applicationInfo.labelRes);
                        }
                    }
                } catch (Resources.NotFoundException nfe) {
                    localizedAppName = null;
                    nfe.printStackTrace();
                }
            }
        }

    }

    protected Resources getResourceFromPackage(String pgk) {
        Resources resources;
        PackageManager pm = mContext.getPackageManager();
        try {
            resources = pm.getResourcesForApplication(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            resources = null;
            e.printStackTrace();
        }

        return resources;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getLocalizedAppName() {
        return this.localizedAppName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mContext.getString(R.string.app_info_pkg));
        sb.append(this.packageName);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_name));
        sb.append(this.appName);
        if (localizedAppName != null) {
            sb.append("\n");
            sb.append(mContext.getString(R.string.app_info_localized_name, locale));
            sb.append(this.localizedAppName);
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_name));
        sb.append(this.versionName);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_code));
        sb.append(this.versionCode);

        return sb.toString();
    }
}
