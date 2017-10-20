package ru.doubletapp.android.izjuminka.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    public static Intent createStartIntent(Context fromContext) {
        return new Intent(fromContext, MainActivity.class);
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        private static final int POSITION_NEWS = 0;
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
