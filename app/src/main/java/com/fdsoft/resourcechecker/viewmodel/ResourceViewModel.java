package com.fdsoft.resourcechecker.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fdsoft.resourcechecker.R;
import com.fdsoft.resourcechecker.model.AppArray;
import com.fdsoft.resourcechecker.model.AppBoolean;
import com.fdsoft.resourcechecker.model.AppDrawable;
import com.fdsoft.resourcechecker.model.AppInteger;
import com.fdsoft.resourcechecker.model.AppIntegerArray;
import com.fdsoft.resourcechecker.model.AppPackage;
import com.fdsoft.resourcechecker.model.AppString;
import com.fdsoft.resourcechecker.model.AppStringArray;
import com.fdsoft.resourcechecker.model.Packages;

import java.util.ArrayList;

import static com.fdsoft.resourcechecker.utils.Const.DRAWABLE_RES;
import static com.fdsoft.resourcechecker.utils.Const.MIPMAP_RES;

public class ResourceViewModel extends ViewModel {

    public ObservableField<String> resourceLocaleName = new ObservableField<>("");
    public ObservableField<String> resourceName = new ObservableField<>("");
    public ObservableField<String> resourceInfo = new ObservableField<>("");
    public ObservableField<Drawable> resourceDrawable = new ObservableField<>();

    public String resourceType = null;
    public String resourcePackage = null;

    public ObservableField<ArrayList<String>> packages = new ObservableField<>();
    public ObservableField<String[]> resType = new ObservableField<>();

    private Context mContext;
    private Packages mPackage;

    public void setContext(Context context) {
        mContext = context;

        mPackage = new Packages(mContext);

        packages.set(mPackage.getPackages());
        resType.set(mContext.getResources().getStringArray(R.array.resource_type));
    }

    public void resSpinnerSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        resourceType = pos == 0 ? null : (String) parent.getSelectedItem();
    }

    public void pkgSpinnerSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        resourcePackage = pos == 0 ? null : (String) parent.getSelectedItem();
    }

    public void onClickClear() {
        // When clicking on Clear Button, clear the values from Edit and Text Fields
        resourceLocaleName.set("");
        resourceName.set("");
        resourceInfo.set("");
        resourceDrawable.set(null);
    }

    public void onClickRunCheck()
    {
        if (resourcePackage == null) {
            Toast.makeText(mContext, mContext.getText(R.string.pkg_not_informed), Toast.LENGTH_SHORT).show();
        } else {
            AppPackage pkg = null;

            if (mContext.getString(R.string.res_integer)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppInteger(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_string)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppString(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_boolean)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppBoolean(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_array)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppArray(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_string_array)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppStringArray(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_integer_array)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppIntegerArray(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get());
            } else if (mContext.getString(R.string.res_drawable)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppDrawable(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get(), DRAWABLE_RES);
            } else if (mContext.getString(R.string.res_mipmap)
                    .equals(resourceType) && !TextUtils.isEmpty(resourceName.get())) {
                pkg = new AppDrawable(mContext, resourcePackage, resourceLocaleName.get(), resourceName.get(), MIPMAP_RES);
            }

            if (pkg == null) {
                pkg = new AppPackage(mContext, resourcePackage, resourceLocaleName.get());
            }

            resourceInfo.set(pkg.toString());

            if (pkg instanceof AppDrawable) {
                resourceDrawable.set(((AppDrawable) pkg).getDrawableValue());
            }
        }
    }
}
