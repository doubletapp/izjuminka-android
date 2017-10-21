package ru.doubletapp.android.izjuminka.view.news;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by hash on 21/10/2017.
 */

public class PhotoViewerFragment extends BaseFragment {

    private ViewPager mImagesView;

    private static final String ARGS_URLS = "args_urls";
    private ArrayList<String> mUrls = new ArrayList<>();

     public static PhotoViewerFragment newInstance(ArrayList<String> urls) {

        Bundle args = new Bundle();

        args.putStringArrayList(ARGS_URLS, urls);

        PhotoViewerFragment fragment = new PhotoViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrls = getArguments().getStringArrayList(ARGS_URLS);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mImagesView = new DynamicViewPager(getContext());
        mImagesView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImagesView.setAdapter(new ImagesPagerAdapter(mUrls));
        mImagesView.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
        return mImagesView;
    }

    private class ImagesPagerAdapter extends PagerAdapter {

        private ArrayList<View> mImagesView;

        ImagesPagerAdapter(ArrayList<String> images) {
            mImagesView = new ArrayList<>();
            for (String url: images) {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(getContext()).load(url).into(imageView);
                mImagesView.add(mImagesView.size(), imageView);
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View imageView = mImagesView.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
