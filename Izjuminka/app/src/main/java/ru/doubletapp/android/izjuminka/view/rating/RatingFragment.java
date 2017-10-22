package ru.doubletapp.android.izjuminka.view.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.api.rating.RatingUser;
import ru.doubletapp.android.izjuminka.presenter.rating.RatingPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class RatingFragment extends BaseFragment<RatingPresenter> {

    private RatingAdapter mAdapter;
    @BindView(R.id.rating_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.rating_refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rating_toolbar)
    Toolbar mToolbar;

    public static RatingFragment newInstance() {

        Bundle args = new Bundle();

        RatingFragment fragment = new RatingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    protected RatingPresenter createPresenter() {
        return IzjuminkaApplication.getApplicationComponent(getContext())
                .getRatingPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mToolbar.setTitle(getString(R.string.title_rating));
        mToolbar.setTitleTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        mAdapter = new RatingAdapter(getContext());
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getUsers();
        });
    }

    public void showRating(List<RatingUser> users) {
        mAdapter.setUsers(users);
    }

    public void setLoading(boolean loading) {
        mRefreshLayout.setRefreshing(loading);
    }
}
