package ru.doubletapp.android.izjuminka.view.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        mAdapter = new RatingAdapter(getContext());
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter);
    }

    public void showRating(List<RatingUser> users) {
        mAdapter.setUsers(users);
    }
}
