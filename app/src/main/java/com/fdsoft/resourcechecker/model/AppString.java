package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.fdsoft.resourcechecker.R;

import java.util.Locale;

import static com.fdsoft.resourcechecker.utils.Const.STRING_RES;

public class AppString extends AppPackage {

    private String mStringRes;
    private String mStringValue;
    private String mLocale;
    private Context mContext;

    public AppString(Context context, String packageName, String locale, String stringRes) {
        super(context, packageName, locale);

        this.mStringRes = stringRes;
        this.mLocale = locale;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources == null) {
                mStringValue = null;
            } else {
                if (!TextUtils.isEmpty(locale)) {
                    Configuration configuration = resources.getConfiguration();

                    String[] splitLocale = null;
                    if (locale.contains("-") || locale.contains("_")) {
                        splitLocale = locale.split("-|_");

                        if (splitLocale.length == 2) {
                            configuration.setLocale(new Locale(splitLocale[0],
                                    splitLocale[1].length() == 2
                                            ? splitLocale[1]
                                            : splitLocale[1].substring(1, splitLocale[1].length())));
                        } else if (splitLocale.length == 3) {
                            configuration.setLocale(new Locale(splitLocale[0],
                                    splitLocale[1].length() == 2
                                            ? splitLocale[1]
                                            : splitLocale[1].substring(1, splitLocale[1].length()),
                                    splitLocale[2]));
                        } else {
                            configuration.setLocale(new Locale(locale));
                        }
                    } else {
                        configuration.setLocale(new Locale(locale));
                    }

                    resources.updateConfiguration(configuration, null);
                }
                mStringValue = resources.getString(resources.getIdentifier(stringRes, STRING_RES, packageName));
            }
        } catch (Resources.NotFoundException nfe) {
            mStringValue = null;
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
        sb.append(mContext.getString(R.string.app_info_string_name));
        sb.append(this.mStringRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_cust_locale));
        if (TextUtils.isEmpty(this.mLocale)) {
            sb.append(mContext.getString(R.string.app_info_no_cust_locale));
        } else {
            sb.append(this.mLocale);
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_string_value));
        if (this.mStringValue == null) {
            sb.append(mContext.getString(R.string.app_info_string_not_found));
        } else {
            sb.append(this.mStringValue);
        }

        return sb.toString();
    }
}
