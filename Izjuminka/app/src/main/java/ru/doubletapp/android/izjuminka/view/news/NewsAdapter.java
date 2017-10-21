package ru.doubletapp.android.izjuminka.view.news;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
        Glide.with(mContext)
                .load(mData.get(position).getImages().get(0))
                .apply(RequestOptions.centerCropTransform())
                .into(holder.mImage);

        holder.mTitle.setText(mData.get(position).getDescription());

        holder.itemView.setOnClickListener(view -> {
            if (holder.mIsCollapse) {
                DisplayMetrics metrics = new DisplayMetrics();
                ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);

                int width = metrics.widthPixels;
                holder.itemView.getLayoutParams().width = width;
                holder.itemView.getLayoutParams().height = width;
            } else {
                holder.itemView.getLayoutParams().width = mContext.getResources().getDimensionPixelSize(R.dimen.news_collaps_size);
                holder.itemView.getLayoutParams().height = mContext.getResources().getDimensionPixelSize(R.dimen.news_collaps_size);
            }
            holder.mIsCollapse = !holder.mIsCollapse;
            notifyItemChanged(position, new Object());
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_image)
        ImageView mImage;
        @BindView(R.id.news_title)
        TextView mTitle;
        boolean mIsCollapse = false;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
