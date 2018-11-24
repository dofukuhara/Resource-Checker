package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.INTEGER_RES;

public class AppInteger extends AppPackage{

    private String mIntRes;
    private String mLocale;
    private Integer mIntValue;
    private Context mContext;

    public AppInteger(Context context, String packageName, String locale, String intRes) {
        super(context, packageName, locale);

        this.mContext = context;
        this.mIntRes = intRes;
        this.mLocale = locale;
        Resources resources = getResourceFromPackage(packageName);

        try {
            mIntValue = resources == null ? null :
                    resources.getInteger(resources.getIdentifier(intRes, INTEGER_RES, packageName));
        } catch (Resources.NotFoundException nfe) {
            mIntValue = null;
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
        sb.append(mContext.getString(R.string.app_info_integer_name));
        sb.append(this.mIntRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_integer_value));
        if (this.mIntValue == null) {
            sb.append(mContext.getString(R.string.app_info_integer_not_found));
        } else {
            sb.append(this.mIntValue);
        }

        return sb.toString();
    }
}
