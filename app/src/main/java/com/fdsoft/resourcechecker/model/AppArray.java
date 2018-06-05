package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppArray extends AppPackage{

    private String locale;
    private String arrayRes;
    private TypedArray typedArrayValue;
    private Context mContext;

    public AppArray(Context context, String packageName, String locale, String arrayRes) {
        super(context, packageName, locale);

        this.locale = locale;
        this.arrayRes = arrayRes;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources != null) {
                typedArrayValue = resources.obtainTypedArray(resources.getIdentifier(arrayRes, ARRAY_RES, packageName));
            } else {
                typedArrayValue = null;
            }
        } catch (Resources.NotFoundException nfe) {
            typedArrayValue = null;
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
        sb.append(mContext.getString(R.string.app_info_array_name));
        sb.append(this.arrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_array_value));
        if (typedArrayValue != null) {
            for (int i = 0; i < typedArrayValue.length() ; i++) {
                sb.append("\n    [");
                sb.append(typedArrayValue.peekValue(i).coerceToString());
                sb.append("]");
            }
        } else {
            sb.append(mContext.getString(R.string.app_info_array_not_found));
        }

        return sb.toString();
    }
}
