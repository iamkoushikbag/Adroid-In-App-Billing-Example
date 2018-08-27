package com.activities.koushik.ufsfreepaiddemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mindtree on 8/22/2018.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private static LoginFragment mLoginFragment;
    private TextView vTVRegister;
    private Button vBtnLogin;
    private FragmentManager mFragmentManager;
    private Context mContext;

    public static LoginFragment getInstance() {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
        }
        return mLoginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_login, container, false);
        vTVRegister = view.findViewById(R.id.tv_register_here);
        vBtnLogin = view.findViewById(R.id.login_button);

        mFragmentManager = getFragmentManager();

        vTVRegister.setOnClickListener(this);
        vBtnLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginFragment = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_here:
                mFragmentManager.beginTransaction()
                        .addToBackStack("Registration")
                        .replace(R.id.fl_container,
                                RegistrationFragment.getInstance(), "Registration")
                        .commit();
                break;
            case R.id.login_button:
                mContext.startActivity(new Intent(getActivity(), HomePageActivity.class));
                break;
            default:
                break;
        }
    }
}
