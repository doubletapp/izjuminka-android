package ru.doubletapp.android.izjuminka.view;

import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.view.news.NewsFragment;
import ru.doubletapp.android.izjuminka.view.news.addNews.AddNewsActivity;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    public static Intent createStartIntent(Context fromContext) {
        return new Intent(fromContext, MainActivity.class);
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.main_navigation)
    BottomNavigationView mBottomNavigation;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    mViewPager.setCurrentItem(TabPagerAdapter.POSITION_NEWS);
                    break;
                case R.id.navigation_profile:
                    mViewPager.setCurrentItem(TabPagerAdapter.POSITION_PROFILE);
                    break;
                case R.id.navigation_rating:
                    mViewPager.setCurrentItem(TabPagerAdapter.POSITION_RATING);
                    break;
            }
            return true;
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case TabPagerAdapter.POSITION_NEWS:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_news);
                        break;
                    case TabPagerAdapter.POSITION_PROFILE:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_profile);
                        break;
                    case TabPagerAdapter.POSITION_RATING:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_rating);
                        break;
                }
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });
    }

    @Override
    public void openAddNews(@Nullable News news) {
        startActivity(AddNewsActivity.createStartIntent(this, news));
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        public static final int POSITION_NEWS = 0;
        private static final int POSITION_PROFILE = 1;
        private static final int POSITION_RATING = 2;

        private static final int FRAGMENTS_COUNT = 3;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case POSITION_PROFILE:
                    return ProfileFragment.newInstance();
                case POSITION_NEWS:
                    return NewsFragment.newInstance();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return FRAGMENTS_COUNT;
        }
    }
}
