package ru.doubletapp.android.izjuminka.view.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;

import android.support.annotation.NonNull;
import android.widget.RelativeLayout;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

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
        holder.itemView.setOnClickListener(view -> {
            if (holder.mCollapse.getVisibility() == View.VISIBLE) {
                holder.mCollapse.setVisibility(View.GONE);
                holder.mExpand.setVisibility(View.VISIBLE);
            } else {
                holder.mCollapse.setVisibility(View.VISIBLE);
                holder.mExpand.setVisibility(View.GONE);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_state_collapse)
        RelativeLayout mCollapse;
        @BindView(R.id.news_state_expand)
        RelativeLayout mExpand;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
