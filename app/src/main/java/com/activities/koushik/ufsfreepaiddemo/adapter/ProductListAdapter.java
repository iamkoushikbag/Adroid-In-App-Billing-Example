package com.activities.koushik.ufsfreepaiddemo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activities.koushik.ufsfreepaiddemo.R;
import com.activities.koushik.ufsfreepaiddemo.model.ProductModel;
import com.activities.koushik.ufsfreepaiddemo.storage.SharedPrefUtil;

import java.util.List;

/**
 * Created by Mindtree on 8/23/2018.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private Context mContext;
    private List<ProductModel> mItemList;

    public ProductListAdapter(Context mContext, List<ProductModel> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.vIVProductThumbnail.setImageResource(mItemList.get(position).getImageUrl());
        holder.vTVPrice.setText(mItemList.get(position).getPrice());
        holder.vTVQuantity.setText(mItemList.get(position).getQuantity());
        holder.vTVProductName.setText(mItemList.get(position).getProductName());

        holder.vBtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPrefUtil.getPurchased(mContext)) {
                    Toast.makeText(mContext, "Successfully added to cart", Toast.LENGTH_LONG).show();
                } else {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Error")
                            .setMessage("Purchase the application to get full access. \n " +
                                    "To purchase hit the Google pay button.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private Button vBtnAddToCart;
        private TextView vTVPrice;
        private TextView vTVQuantity;
        private TextView vTVProductName;
        private ImageView vIVProductThumbnail;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            vBtnAddToCart = itemView.findViewById(R.id.btn_ad_to_cart);
            vTVPrice = itemView.findViewById(R.id.tv_product_price);
            vTVQuantity = itemView.findViewById(R.id.tv_product_qty);
            vTVProductName = itemView.findViewById(R.id.tv_product_name);
            vIVProductThumbnail = itemView.findViewById(R.id.iv_product);
        }
    }
}
