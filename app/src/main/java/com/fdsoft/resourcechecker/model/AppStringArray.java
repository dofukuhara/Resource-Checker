package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppStringArray extends AppPackage {

    private String mStringArrayRes;
    private String[] mStringArrayValue;
    private String mLocale;
    private Context mContext;

    public AppStringArray(Context context, String packageName, String locale, String stringArrayRes) {
        super(context, packageName, locale);

        this.mStringArrayRes = stringArrayRes;
        this.mLocale = locale;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources == null) {
                mStringArrayValue = null;
            } else {
                mStringArrayValue = resources.getStringArray(
                        resources.getIdentifier(stringArrayRes,
                                ARRAY_RES, packageName));
            }
        } catch (Resources.NotFoundException nfe) {
            mStringArrayValue = null;
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
        sb.append(mContext.getString(R.string.app_info_string_array_name));
        sb.append(this.mStringArrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_string_array_value));
        if (mStringArrayValue != null) {
            for (String item : mStringArrayValue) {
                sb.append("\n    [");
                sb.append(item);
                sb.append("]");
            }
        } else {
            sb.append(mContext.getString(R.string.app_info_string_array_not_found));
        }

        return sb.toString();
    }
}
