package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.BOOLEAN_RES;

public class AppBoolean extends AppPackage{

    private String mBooleanRes;
    private Boolean mBooleanValue;
    private String mLocale;
    private Context mContext;

    public AppBoolean(Context context, String packageName, String locale, String booleanRes) {
        super(context, packageName, locale);

        this.mBooleanRes = booleanRes;
        this.mContext = context;
        this.mLocale = locale;
        Resources resources = getResourceFromPackage(packageName);

        try {
            mBooleanValue = resources == null ? null :
                    resources.getBoolean(resources.getIdentifier(booleanRes, BOOLEAN_RES, packageName));
        } catch (Resources.NotFoundException nfe) {
            mBooleanValue = null;
            nfe.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mContext.getString(R.string.app_info_pkg));
        sb.append(this.getPackageName());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_name));
        sb.append(this.getmAppName());
        if (getmLocalizedAppName() != null) {
            sb.append("\n");
            sb.append(mContext.getString(R.string.app_info_localized_name, mLocale));
            sb.append(getmLocalizedAppName());
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_name));
        sb.append(this.getmVersionName());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_ver_code));
        sb.append(this.getmVersionCode());
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_boolean_name));
        sb.append(this.mBooleanRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_boolean_value));
        if (mBooleanValue != null) {
            sb.append(mBooleanValue);
        } else {
            sb.append(mContext.getString(R.string.app_info_boolean_not_found));
        }

        return sb.toString();
    }
}
