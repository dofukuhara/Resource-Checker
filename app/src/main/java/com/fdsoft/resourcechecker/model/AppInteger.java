package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.INTEGER_RES;

public class AppInteger extends AppPackage{

    private String intRes;
    private String locale;
    private Integer intValue;
    private Context mContext;

    public AppInteger(Context context, String packageName, String locale, String intRes) {
        super(context, packageName, locale);

        this.mContext = context;
        this.intRes = intRes;
        this.locale = locale;
        Resources resources = getResourceFromPackage(packageName);

        try {
            intValue = resources == null ? null :
                    resources.getInteger(resources.getIdentifier(intRes, INTEGER_RES, packageName));
        } catch (Resources.NotFoundException nfe) {
            intValue = null;
            nfe.printStackTrace();
        }
    }

    public int getIntValue() {
        return this.intValue;
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
        sb.append(mContext.getString(R.string.app_info_integer_name));
        sb.append(this.intRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_integer_value));
        if (this.intValue == null) {
            sb.append(mContext.getString(R.string.app_info_integer_not_found));
        } else {
            sb.append(this.intValue);
        }

        return sb.toString();
    }
}
