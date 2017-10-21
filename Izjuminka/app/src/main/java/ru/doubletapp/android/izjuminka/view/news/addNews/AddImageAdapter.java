package ru.doubletapp.android.izjuminka.view.news.addNews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.utils.StringUtils;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.ImageHolder> {

    private final Context mContext;
    private List<String> mImages = new ArrayList<>();
    @Nullable
    private AddImageListener mListener;

    public AddImageAdapter(Context context, @Nullable AddImageListener listener) {
        mContext = context;
        mListener = listener;
        //for +
        mImages.add("");
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_image, parent, false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        String url = mImages.get(position);
        if (!StringUtils.isNullOrEmpty(url)) {
            Glide.with(mContext)
                    .load(url)
                    .apply(RequestOptions.skipMemoryCacheOf(true))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.mImage);
            holder.mImage.setOnClickListener(view -> {
            });
        } else {
            holder.mImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_add_box));
            holder.mImage.setOnClickListener(view -> {
                if (mListener != null) mListener.onAddClick();
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<String> images) {
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    public void addImage(String url) {
        if (!mImages.contains(url))
            mImages.add(0, url);
        notifyDataSetChanged();
    }

    public List<String> getItems() {
        mImages.remove("");
        return mImages;
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

    public interface AddImageListener {

        void onAddClick();

    }
}
