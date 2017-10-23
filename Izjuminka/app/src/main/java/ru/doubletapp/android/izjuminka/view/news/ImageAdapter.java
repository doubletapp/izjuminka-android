package ru.doubletapp.android.izjuminka.view.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private Context mContext;
    private List<String> mImages = new ArrayList<>();
    private NewsAdapter.OnMemasClickListener mListener;

    public ImageAdapter(Context context, NewsAdapter.OnMemasClickListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_image, parent, false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        Glide.with(mContext)
                .load(mImages.get(position))
                .apply(RequestOptions.centerCropTransform())
                .into(holder.mImage);
        holder.itemView.setOnClickListener(view -> mListener.onMemasClick((ArrayList<String>) mImages));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<String> mImages) {
        this.mImages = mImages;
        notifyDataSetChanged();
    }

    public ArrayList<String> getImages() {
        return (ArrayList<String>) mImages;
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_image)
        ImageView mImage;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setIsRecyclable(false);
        }
    }
}
