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

    private String mPackageName;
    private String mLocale;
    private String mAppName;
    private String mLocalizedAppName;
    private String mVersionName;
    private int mVersionCode;

    private Context mContext;

    public AppPackage(Context context, String packageName, String locale) {
        mContext = context;
        this.mLocale = locale;
        this.mPackageName = packageName;

        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi;


        try {
            pi = pm.getPackageInfo(this.mPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            pi = null;
            e.printStackTrace();
        }

        if (pi != null) {
            this.mAppName = pi.applicationInfo.loadLabel(pm).toString();
            this.mVersionName = pi.versionName;
            this.mVersionCode = pi.versionCode;

            if (TextUtils.isEmpty(locale)) {
                mLocalizedAppName = null;
            } else {
                Resources resources = getResourceFromPackage(packageName);

                try {
                    if (resources == null) {
                        mLocalizedAppName = null;
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
                            mLocalizedAppName = resources.getString(pi.applicationInfo.labelRes);
                        }
                    }
                } catch (Resources.NotFoundException nfe) {
                    mLocalizedAppName = null;
                    nfe.printStackTrace();
                }
            }
        }

    }

    protected Resources getResourceFromPackage(String pgk) {
        Resources resources;
        PackageManager pm = mContext.getPackageManager();
        try {
            resources = pm.getResourcesForApplication(mPackageName);
        } catch (PackageManager.NameNotFoundException e) {
            resources = null;
            e.printStackTrace();
        }

        return resources;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getmAppName() {
        return this.mAppName;
    }

    public String getmLocalizedAppName() {
        return this.mLocalizedAppName;
    }

    public String getmVersionName() {
        return this.mVersionName;
    }

    public int getmVersionCode() {
        return this.mVersionCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mContext.getString(R.string.app_info_pkg));
        sb.append(this.mPackageName);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_name));
        sb.append(this.mAppName);
        if (mLocalizedAppName != null) {
            sb.append("\n");
            sb.append(mContext.getString(R.string.app_info_localized_name, mLocale));
            sb.append(this.mLocalizedAppName);
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_name));
        sb.append(this.mVersionName);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_code));
        sb.append(this.mVersionCode);

        return sb.toString();
    }
}
