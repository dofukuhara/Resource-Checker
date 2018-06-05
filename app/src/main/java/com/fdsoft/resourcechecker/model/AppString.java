package com.fdsoft.resourcechecker.model;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.fdsoft.resourcechecker.R;

import java.util.Locale;

import static com.fdsoft.resourcechecker.utils.Const.STRING_RES;

public class AppString extends AppPackage {

    private String stringRes;
    private String stringValue;
    private String locale;
    private Context mContext;

    public AppString(Context context, String packageName, String locale, String stringRes) {
        super(context, packageName, locale);

        this.stringRes = stringRes;
        this.locale = locale;
        this.mContext = context;
        Resources resources = getResourceFromPackage(packageName);

        try {
            if (resources == null) {
                stringValue = null;
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
                stringValue = resources.getString(resources.getIdentifier(stringRes, STRING_RES, packageName));
            }
        } catch (Resources.NotFoundException nfe) {
            stringValue = null;
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
        sb.append(mContext.getString(R.string.app_info_string_name));
        sb.append(this.stringRes);
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_cust_locale));
        if (TextUtils.isEmpty(this.locale)) {
            sb.append(mContext.getString(R.string.app_info_no_cust_locale));
        } else {
            sb.append(this.locale);
        }
        sb.append("\n");
        sb.append(mContext.getString(R.string.app_info_string_value));
        if (this.stringValue == null) {
            sb.append(mContext.getString(R.string.app_info_string_not_found));
        } else {
            sb.append(this.stringValue);
        }

        return sb.toString();
    }
}
