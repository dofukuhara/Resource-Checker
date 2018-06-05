package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppIntegerArray extends AppPackage{

    private String intArrayRes;
    private int[] intArrayValue;
    private String locale;
    private Context mContext;

    public AppIntegerArray(Context context, String packageName, String locale, String intArrayRes) {
        super(context, packageName, locale);

        this.intArrayRes = intArrayRes;
        this.locale = locale;
        this.mContext = context;

        Resources resources = getResourceFromPackage(packageName);

        if (resources == null) {
            intArrayValue = null;
        } else {
            try {
                intArrayValue = resources.getIntArray(
                        resources.getIdentifier(intArrayRes, ARRAY_RES, packageName)
                );
            } catch (Resources.NotFoundException nfe) {
                intArrayValue = null;
                nfe.printStackTrace();
            }
        }
    }

    public int[] getIntArrayValue() {
        return this.intArrayValue;
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
        sb.append(mContext.getString(R.string.app_info_int_array_name));
        sb.append(this.intArrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_int_array_value));
        if (intArrayValue != null) {
            for (int i = 0; i < intArrayValue.length ; i++) {
                sb.append("\n    [");
                sb.append(intArrayValue[i]);
                sb.append("]");
            }
        } else {
            sb.append(mContext.getString(R.string.app_info_int_array_not_found));
        }

        return sb.toString();
    }
}
