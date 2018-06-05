package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.fdsoft.resourcechecker.R;

import static com.fdsoft.resourcechecker.utils.Const.DRAWABLE_RES;

public class AppDrawable extends AppPackage {

    private String drawableRes;
    private Drawable drawableValue;
    private String locale;
    private String imgType;
    private Context mContext;

    public AppDrawable(Context context, String packageName, String locale, String drawableRes, String imgType) {
        super(context, packageName, locale);

        this.drawableRes = drawableRes;
        this.locale = locale;
        this.imgType = imgType;
        this.mContext = context;

        Resources resources = getResourceFromPackage(packageName);

        if (resources == null) {
            drawableValue = null;
        } else {
            try {
                drawableValue = resources.getDrawable(
                        resources.getIdentifier(drawableRes, imgType, packageName),
                        null);
            } catch (Resources.NotFoundException nfe) {
                drawableRes = null;
                nfe.printStackTrace();
            }
        }
    }

    public Drawable getDrawableValue() {
        return drawableValue;
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

        if (DRAWABLE_RES.equals(imgType)) {
            sb.append(mContext.getString(R.string.app_info_drawable_name));
        } else {
            // MIPMAP_RES
            sb.append(mContext.getString(R.string.app_info_mipmap_name));
        }
        sb.append(this.drawableRes);
        sb.append("\n");

        if (DRAWABLE_RES.equals(imgType)) {
            sb.append(mContext.getString(R.string.app_info_drawable_value));
        } else {
            // MIPMAP_RES
            sb.append(mContext.getString(R.string.app_info_mipmap_value));
        }
        if (drawableValue == null) {
            if (DRAWABLE_RES.equals(imgType)) {
                sb.append(mContext.getString(R.string.app_info_drawable_not_found));
            } else {
                sb.append(mContext.getString(R.string.app_info_mipmap_not_found));
            }
        }

        return sb.toString();
    }
}
