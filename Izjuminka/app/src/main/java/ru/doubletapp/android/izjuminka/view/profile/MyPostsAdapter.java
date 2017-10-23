package ru.doubletapp.android.izjuminka.view.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.api.profile.MyPostsResponse;
import ru.doubletapp.android.izjuminka.utils.StringUtils;

/**
 * Created by hash on 21/10/2017.
 */

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyPostsViewHolder> {

    public static final String STATUS_ACCEPTED = "accepted";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_REJECTED = "rejected";
    public static final String STATUS_REWARDED = "rewarded";
    public static final String STATUS_INPROGRESS = "in_progress";

    @NonNull
    private List<MyPostsResponse.Results> mPosts = new ArrayList<>();

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

    public void setData(List<MyPostsResponse.Results> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    class MyPostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_post_title)
        TextView mTitleView;
        @BindView(R.id.my_post_body)
        TextView mBodyView;
        @BindView(R.id.my_post_status)
        TextView mStatusView;
        @BindView(R.id.my_post_image)
        ImageView mImageView;

        @BindColor(R.color.statusAccepted)
        @ColorInt int mColorAccepted;
        @BindColor(R.color.statusPending)
        @ColorInt int mColorPending;
        @BindColor(R.color.statusRejected)
        @ColorInt int mColorRejected;
        @BindColor(R.color.statusRewarded)
        @ColorInt int mColorRewarded;
        @BindColor(R.color.colorPrimary)
        @ColorInt int mColorPrimary;
        @BindColor(android.R.color.tab_indicator_text)
        @ColorInt int mColorDefault;

        public MyPostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(MyPostsResponse.Results data) {
            mTitleView.setText(StringUtils.getShortString(data.getDescription()));
            if (!StringUtils.isNullOrEmpty(data.getPhoto())) {
                Glide.with(mImageView.getContext())
                        .load(data.getPhoto())
                        .apply(RequestOptions.centerCropTransform())
                        .into(mImageView);
            }
            switch (data.getValidateStatus()) {
                case STATUS_ACCEPTED:
                    mStatusView.setTextColor(mColorAccepted);
                    mStatusView.setText(R.string.status_acceped);
                    mBodyView.setClickable(true);
                    mBodyView.setTextColor(mColorPrimary);
                    mBodyView.setText(R.string.view_post);
                    mBodyView.setOnClickListener(view -> {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getVkUrl()));
                        mBodyView.getContext().startActivity(browserIntent);
                    });
                    break;
                case STATUS_PENDING:
                case STATUS_INPROGRESS:
                    mStatusView.setTextColor(mColorPending);
                    mStatusView.setText(R.string.status_pending);
                    mBodyView.setText(R.string.pending_expl);
                    mBodyView.setClickable(false);
                    mBodyView.setTextColor(mColorDefault);
                    break;
                case STATUS_REJECTED:
                    mStatusView.setTextColor(mColorRejected);
                    mStatusView.setText(R.string.staus_rejected);
                    mBodyView.setText(data.getValidateMessage());
                    mBodyView.setClickable(false);
                    mBodyView.setTextColor(mColorDefault);
                    break;
                case STATUS_REWARDED:
                    mStatusView.setTextColor(mColorRewarded);
                    String status = mStatusView.getContext().getResources().getString(R.string.status_rewarded);
                    String withCash = status + ": " + data.getCash() + "\u20BD";
                    mStatusView.setText(withCash);
                    mBodyView.setClickable(true);
                    mBodyView.setText(R.string.view_post);
                    mBodyView.setTextColor(mColorPrimary);
                    mBodyView.setOnClickListener(view -> {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getVkUrl()));
                        mBodyView.getContext().startActivity(browserIntent);
                    });
                    break;

            }
        }
    }

}
