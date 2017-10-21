package ru.doubletapp.android.izjuminka.view.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    @NonNull
    private Context mContext;

    public NewsAdapter(@NonNull Context context) {
        mContext = context;
    }

    @NonNull
    private List<News> mData = new ArrayList<>();

    public void setData(@NonNull List<News> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_news, parent, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

        bind(holder, position);

        holder.itemView.setOnClickListener(view -> {
            if (holder.mCollapseContainer.getVisibility() == View.VISIBLE) {
                bindExchange(holder, position);
            } else {
                bindCollapse(holder, position);
            }
        });
    }

    private void bind(NewsHolder holder, int position) {
        Glide.with(mContext)
                .load(mData.get(position).getImages().get(0))
                .apply(RequestOptions.centerCropTransform())
                .into(holder.mCollapseImage);

        holder.mCollapseDescription.setText(mData.get(position).getDescription());
        holder.mExchangeDescription.setText(mData.get(position).getDescription());
        if (mData.get(position).isExchanged()) {
            bindExchange(holder, position);
        } else {
            bindCollapse(holder, position);
        }
        holder.setImages(mData.get(position).getImages());

    }

    private void bindCollapse(NewsHolder holder, int position) {
        mData.get(position).setExchanged(false);

        holder.mCollapseContainer.setVisibility(View.VISIBLE);
        holder.mExchangeContainer.setVisibility(View.GONE);

        holder.itemView.invalidate();
        holder.itemView.requestLayout();
    }

    private void bindExchange(NewsHolder holder, int position) {
        mData.get(position).setExchanged(true);
        holder.mCollapseContainer.setVisibility(View.GONE);
        holder.mExchangeContainer.setVisibility(View.VISIBLE);

        holder.itemView.invalidate();
        holder.itemView.requestLayout();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_collapse)
        CardView mCollapseContainer;
        @BindView(R.id.news_exchange)
        CardView mExchangeContainer;

        @BindView(R.id.news_collapsed_image)
        ImageView mCollapseImage;
        @BindView(R.id.news_collapsed_description)
        TextView mCollapseDescription;

        @BindView(R.id.news_exchange_description)
        TextView mExchangeDescription;
        @BindView(R.id.news_exchange_recycler)
        RecyclerView mExchangeRecycler;

        private ImageAdapter mAdapter;


        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setIsRecyclable(false);

            mAdapter = new ImageAdapter(mContext);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            mExchangeRecycler.setLayoutManager(mLayoutManager);
            mExchangeRecycler.setAdapter(mAdapter);
        }

        public void setImages(List<String> images) {
            mAdapter.setImages(images);
        }
    }
}