package com.fdsoft.resourcechecker.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fdsoft.resourcechecker.R;
import com.fdsoft.resourcechecker.databinding.ActivityResourceCheckerBinding;
import com.fdsoft.resourcechecker.viewmodel.ResourceViewModel;

public class ResourceCheckerActivity extends AppCompatActivity {

    private ResourceViewModel mResourceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_checker);

        ActivityResourceCheckerBinding activityResourceCheckerBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_resource_checker);


        mResourceViewModel = ViewModelProviders.of(this).get(ResourceViewModel.class);

        activityResourceCheckerBinding.setViewModel(mResourceViewModel);

        mResourceViewModel.setContext(this);
    }
}
