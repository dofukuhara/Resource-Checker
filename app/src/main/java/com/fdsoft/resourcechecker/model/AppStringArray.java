package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppStringArray extends AppPackage {

    private String stringArrayRes;
    private String[] stringArrayValue;
    private String locale;
    private Context mContext;

    public AppStringArray(Context context, String packageName, String locale, String stringArrayRes) {
        super(context, packageName, locale);

        this.stringArrayRes = stringArrayRes;
        this.locale = locale;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources == null) {
                stringArrayValue = null;
            } else {
                stringArrayValue = resources.getStringArray(
                        resources.getIdentifier(stringArrayRes,
                                ARRAY_RES, packageName));
            }
        } catch (Resources.NotFoundException nfe) {
            stringArrayValue = null;
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
        sb.append(mContext.getString(R.string.app_info_string_array_name));
        sb.append(this.stringArrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_string_array_value));
        if (stringArrayValue != null) {
            for (String item : stringArrayValue) {
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
