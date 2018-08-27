package com.activities.koushik.ufsfreepaiddemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mindtree on 8/23/2018.
 */
public class RegistrationFragment extends Fragment {
    private static RegistrationFragment mRegistrationFragment;

    public static RegistrationFragment getInstance() {
        if (mRegistrationFragment == null) {
            mRegistrationFragment = new RegistrationFragment();
        }
        return mRegistrationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_registration, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRegistrationFragment = null;
    }
}
