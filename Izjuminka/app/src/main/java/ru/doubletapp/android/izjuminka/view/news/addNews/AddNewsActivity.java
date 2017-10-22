package ru.doubletapp.android.izjuminka.view.news.addNews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.utils.ActivityUtils;
import ru.doubletapp.android.izjuminka.view.BaseActivity;
import ru.doubletapp.android.izjuminka.view.MainActivity;
import ru.yandex.speechkit.SpeechKit;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class AddNewsActivity extends BaseActivity {

    public final static String KEY_NEWS = "news";
    @BindView(R.id.add_news_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.add_news_container)
    FrameLayout mContainer;


    public static Intent createStartIntent(Context fromContext, @Nullable News news) {
        if (news == null) {
            return new Intent(fromContext, AddNewsActivity.class);
        } else {
            Intent intent = new Intent(fromContext, AddNewsActivity.class);
            Bundle args = new Bundle();
            args.putParcelable(KEY_NEWS, news);
            intent.putExtras(args);
            return intent;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        mUnbinder = ButterKnife.bind(this);
        SpeechKit.getInstance().configure(getApplicationContext(), "b1dcd943-62f3-4c94-86d8-6ee79e37325f");
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.create_post);
        }
        ActivityUtils.replaceFragment(getSupportFragmentManager(), mContainer.getId(), AddNewsFragment.newInstance(),
                AddNewsFragment.TAG, true, getIntent().getExtras());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem add = menu.add(getString(R.string.add_news_add));
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        add.setOnMenuItemClickListener(item -> {
            ((AddNewsFragment)getSupportFragmentManager().findFragmentById(mContainer.getId())).onNextClick();
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getSupportFragmentManager().findFragmentById(mContainer.getId())
                .onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
