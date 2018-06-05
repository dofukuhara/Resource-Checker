package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.fdsoft.resourcechecker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Packages {

    private ArrayList<String> packagesList;

    public Packages (Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> appInfoList = pm.getInstalledApplications(0);

        packagesList = new ArrayList<>();

        packagesList.add(context.getString(R.string.pkg_select));

        for (ApplicationInfo appInfo : appInfoList) {
            packagesList.add(appInfo.packageName);
        }

        Collections.sort(packagesList);
    }

    public ArrayList<String> getPackages() {
        return packagesList;
    }
}
