package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.DIMEN_RES;

public class AppDimen extends AppPackage {
    private Context mContext;
    private String mLocale;
    private String mDimenRes;
    private Float mDimenValueFloat;
    private Float mDimenValueDip;
    private float mScreenDensity;

    public AppDimen(Context context, String packageName, String locale, String dimenRes) {
        super(context, packageName, locale);

        this.mContext = context;
        this.mDimenRes = dimenRes;
        this.mLocale = locale;

        Resources resources = getResourceFromPackage(packageName);

        try {
            mDimenValueFloat = resources == null ? null :
                    resources.getDimension(resources.getIdentifier(
                            mDimenRes,
                            DIMEN_RES,
                            packageName
                    ));
        } catch (Resources.NotFoundException nfe) {
            mDimenValueFloat = null;
            nfe.printStackTrace();
        }

        mScreenDensity = context.getResources().getDisplayMetrics().density;
        mDimenValueDip = mDimenValueFloat == null ? null :
                mDimenValueFloat / mScreenDensity;
    }

    public float getDimenValueFloat() {
        return this.mDimenValueFloat == null ? 0 :
                this.mDimenValueFloat;
    }

    public float getDimenValueDip() {
        return this.mDimenValueDip == null ? 0 :
                this.mDimenValueDip;
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
        sb.append(mContext.getString(R.string.app_info_dimen_name));
        sb.append(this.mDimenRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_screen_density));
        sb.append(this.mScreenDensity);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_dimen_value_float));
        if (this.mDimenValueFloat == null) {
            sb.append(mContext.getString(R.string.app_info_dimen_not_found));
        } else {
            sb.append(this.mDimenValueFloat);
            sb.append("\n");
            sb.append(mContext.getString(R.string.app_info_dimen_value_dip));
            sb.append(this.mDimenValueDip);
        }

        return sb.toString();
    }
}
