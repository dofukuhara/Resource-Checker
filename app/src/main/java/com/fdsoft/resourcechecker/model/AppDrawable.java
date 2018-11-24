package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.DRAWABLE_RES;

public class AppDrawable extends AppPackage {

    private String mDrawableRes;
    private Drawable mDrawableValue;
    private String mLocale;
    private String mImgType;
    private Context mContext;

    public AppDrawable(Context context, String packageName, String locale, String drawableRes, String imgType) {
        super(context, packageName, locale);

        this.mDrawableRes = drawableRes;
        this.mLocale = locale;
        this.mImgType = imgType;
        this.mContext = context;

        Resources resources = getResourceFromPackage(packageName);

        if (resources == null) {
            mDrawableValue = null;
        } else {
            try {
                mDrawableValue = resources.getDrawable(
                        resources.getIdentifier(drawableRes, imgType, packageName),
                        null);
            } catch (Resources.NotFoundException nfe) {
                drawableRes = null;
                nfe.printStackTrace();
            }
        }
    }

    public Drawable getDrawableValue() {
        return mDrawableValue;
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

        if (DRAWABLE_RES.equals(mImgType)) {
            sb.append(mContext.getString(R.string.app_info_drawable_name));
        } else {
            // MIPMAP_RES
            sb.append(mContext.getString(R.string.app_info_mipmap_name));
        }
        sb.append(this.mDrawableRes);
        sb.append("\n");

        if (DRAWABLE_RES.equals(mImgType)) {
            sb.append(mContext.getString(R.string.app_info_drawable_value));
        } else {
            // MIPMAP_RES
            sb.append(mContext.getString(R.string.app_info_mipmap_value));
        }
        if (mDrawableValue == null) {
            if (DRAWABLE_RES.equals(mImgType)) {
                sb.append(mContext.getString(R.string.app_info_drawable_not_found));
            } else {
                sb.append(mContext.getString(R.string.app_info_mipmap_not_found));
            }
        }

        return sb.toString();
    }
}
