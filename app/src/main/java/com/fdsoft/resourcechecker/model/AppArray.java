package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.ARRAY_RES;

public class AppArray extends AppPackage{

    private String mLocale;
    private String mArrayRes;
    private TypedArray mTypeArrayValue;
    private Context mContext;

    public AppArray(Context context, String packageName, String locale, String arrayRes) {
        super(context, packageName, locale);

        this.mLocale = locale;
        this.mArrayRes = arrayRes;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources != null) {
                mTypeArrayValue = resources.obtainTypedArray(resources.getIdentifier(arrayRes, ARRAY_RES, packageName));
            } else {
                mTypeArrayValue = null;
            }
        } catch (Resources.NotFoundException nfe) {
            mTypeArrayValue = null;
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
        sb.append(mContext.getString(R.string.app_info_array_name));
        sb.append(this.mArrayRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_array_value));
        if (mTypeArrayValue != null) {
            for (int i = 0; i < mTypeArrayValue.length() ; i++) {
                sb.append("\n    [");
                sb.append(mTypeArrayValue.peekValue(i).coerceToString());
                sb.append("]");
            }
        } else {
            sb.append(mContext.getString(R.string.app_info_array_not_found));
        }

        return sb.toString();
    }
}
