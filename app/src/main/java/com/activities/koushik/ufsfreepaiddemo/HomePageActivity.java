package com.activities.koushik.ufsfreepaiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activities.koushik.ufsfreepaiddemo.adapter.ProductListAdapter;
import com.activities.koushik.ufsfreepaiddemo.model.ConstractModel;
import com.activities.koushik.ufsfreepaiddemo.storage.SharedPrefUtil;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

/**
 * Created by Mindtree on 8/23/2018.
 */
public class HomePageActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    private BillingProcessor bp;
    private Toolbar toolbar;
    private LinearLayout vLLPay;
    private TextView vTVTitleFree;
    private boolean isPurchased = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_screen);
        toolbar = findViewById(R.id.app_bar);
        vLLPay = findViewById(R.id.ll_buttom);
        vTVTitleFree = findViewById(R.id.tv_title);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        bp = new BillingProcessor(this, null, this);
        setUpToolbar();

        if (SharedPrefUtil.getPurchased(this)) {
            vLLPay.setVisibility(View.GONE);
            vTVTitleFree.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
        } else {
            vLLPay.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);
            vTVTitleFree.setVisibility(View.VISIBLE);
        }

        ProductListAdapter adapter = new ProductListAdapter(this,
                new ConstractModel().constructDataModel());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //billing processor initialization
        bp = new BillingProcessor(this, null, this);

        vLLPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(HomePageActivity.this, "android.test.purchased");
            }
        });

    }

    private void setUpToolbar() {
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                this,
                findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_action_down), // Menu open icon
                getResources().getDrawable(R.drawable.ic_action_up))); // Menu close icon
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Toast.makeText(this, "App purchased successfully", Toast.LENGTH_LONG).show();
        toolbar.setVisibility(View.VISIBLE);
        vLLPay.setVisibility(View.GONE);
        vTVTitleFree.setVisibility(View.GONE);
        SharedPrefUtil.isPurchased(this, true);
    }

    @Override
    public void onPurchaseHistoryRestored() {
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(this, "App purchased Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
