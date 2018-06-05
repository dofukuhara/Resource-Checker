package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.BOOLEAN_RES;

public class AppBoolean extends AppPackage{

    private String booleanRes;
    private Boolean booleanValue;
    private String locale;
    private Context mContext;

    public AppBoolean(Context context, String packageName, String locale, String booleanRes) {
        super(context, packageName, locale);

        this.booleanRes = booleanRes;
        this.mContext = context;
        this.locale = locale;
        Resources resources = getResourceFromPackage(packageName);

        try {
            booleanValue = resources == null ? null :
                    resources.getBoolean(resources.getIdentifier(booleanRes, BOOLEAN_RES, packageName));
        } catch (Resources.NotFoundException nfe) {
            booleanValue = null;
            nfe.printStackTrace();
        }
    }

    public boolean getBooleanValue() {
        return this.booleanValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mContext.getString(R.string.app_info_pkg));
        sb.append(this.getPackageName());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_name));
        sb.append(this.getAppName());
        if (getLocalizedAppName() != null) {
            sb.append("\n");
            sb.append(mContext.getString(R.string.app_info_localized_name, locale));
            sb.append(getLocalizedAppName());
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_name));
        sb.append(this.getVersionName());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_code));
        sb.append(this.getVersionCode());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_boolean_name));
        sb.append(this.booleanRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_boolean_value));
        if (booleanValue != null) {
            sb.append(booleanValue);
        } else {
            sb.append(mContext.getString(R.string.app_info_boolean_not_found));
        }

        return sb.toString();
    }
}
