package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppIntegerArray extends AppPackage{

    private String mIntArrayRes;
    private int[] mIntArrayValue;
    private String mLocale;
    private Context mContext;

    public AppIntegerArray(Context context, String packageName, String locale, String intArrayRes) {
        super(context, packageName, locale);

        this.mIntArrayRes = intArrayRes;
        this.mLocale = locale;
        this.mContext = context;

        Resources resources = getResourceFromPackage(packageName);

        if (resources == null) {
            mIntArrayValue = null;
        } else {
            try {
                mIntArrayValue = resources.getIntArray(
                        resources.getIdentifier(intArrayRes, ARRAY_RES, packageName)
                );
            } catch (Resources.NotFoundException nfe) {
                mIntArrayValue = null;
                nfe.printStackTrace();
            }
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
        sb.append(mContext.getString(R.string.app_info_int_array_name));
        sb.append(this.mIntArrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_int_array_value));
        if (mIntArrayValue != null) {
            for (int i = 0; i < mIntArrayValue.length ; i++) {
                sb.append("\n    [");
                sb.append(mIntArrayValue[i]);
                sb.append("]");
            }
        } else {
            sb.append(mContext.getString(R.string.app_info_int_array_not_found));
        }

        return sb.toString();
    }
}
