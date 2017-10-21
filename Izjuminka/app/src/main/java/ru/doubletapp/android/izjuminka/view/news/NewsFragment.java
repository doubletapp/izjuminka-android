package ru.doubletapp.android.izjuminka.view.news;

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
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.presenter.news.NewsPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> {

    private NewsAdapter mAdapter;
    @BindView(R.id.news_recycler)
    RecyclerView mRecycler;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    protected NewsPresenter createPresenter() {
        return IzjuminkaApplication.getApplicationComponent(getContext())
                .getNewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this, savedInstanceState);
        init();
    }

    private void init() {
        mAdapter = new NewsAdapter(getContext(), news -> baseCallback.openAddNews(news));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mAdapter);
        mPresenter.getNews();
    }

    public void showNews(List<News> news) {
        mAdapter.setData(news);
    }
}
