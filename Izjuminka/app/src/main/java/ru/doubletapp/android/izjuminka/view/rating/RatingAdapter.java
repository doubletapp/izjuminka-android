package ru.doubletapp.android.izjuminka.view.rating;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
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
import ru.doubletapp.android.izjuminka.api.rating.RatingUser;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {

    private Context mContext;
    private List<RatingUser> users = new ArrayList<>();

    public RatingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_rating, parent, false);
        return new RatingHolder(v);
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        RatingUser user = users.get(position);

        holder.mFirstName.setText(user.getFirstName());
        holder.mLastName.setText(user.getLastName());

        Glide.with(mContext)
                .load(user.getPhoto())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mAvatar);
        holder.mHype.setText(user.getHype());
        holder.mPlace.setText(user.getPosition());
        holder.mContainer.setBackgroundColor(ContextCompat.getColor(mContext, user.isYou() ? R.color.colorPrimaryVeryLight : android.R.color.white));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<RatingUser> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public class RatingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rating_firstname)
        TextView mFirstName;
        @BindView(R.id.rating_lastname)
        TextView mLastName;
        @BindView(R.id.rating_hype)
        TextView mHype;
        @BindView(R.id.rating_place)
        TextView mPlace;
        @BindView(R.id.rating_avatar)
        ImageView mAvatar;
        @BindView(R.id.rating_container)
        CardView mContainer;

        public RatingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
