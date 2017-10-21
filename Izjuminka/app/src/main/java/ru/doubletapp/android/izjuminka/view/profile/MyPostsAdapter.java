package ru.doubletapp.android.izjuminka.view.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;

/**
 * Created by hash on 21/10/2017.
 */

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyPostsViewHolder> {

    @NonNull
    private List<String> mPosts = new ArrayList<>();

    @Override
    public MyPostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_my_post , parent, false);
        return new MyPostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyPostsViewHolder holder, int position) {
        holder.bindData(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void setData(List<String> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    class MyPostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_post_title)
        TextView mTitleView;
        @BindView(R.id.my_post_body)
        TextView mBodyView;

        public MyPostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTitleView.setText("Title");
            mBodyView.setText("Bodyyyy");
        }

        public void bindData(String data) {
            mBodyView.setText(data);
        }
    }

}
