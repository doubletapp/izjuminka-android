package ru.doubletapp.android.izjuminka.view.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import com.paginate.Paginate;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.presenter.news.NewsPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsAdapter.OnMemasClickListener{

    private NewsAdapter mAdapter;
    @BindView(R.id.news_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.news_refresh)
    SwipeRefreshLayout mRefresh;
    private LinearLayoutManager mLayoutManager;
    private Paginate.Callbacks mCallbacks = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            // Load next page of data (e.g. network or database)
            mPresenter.getNews();
        }

        @Override
        public boolean isLoading() {
            // Indicate whether new page loading is in progress or not
            return mPresenter.isLoading();
        }

        @Override
        public boolean hasLoadedAllItems() {
            // Indicate whether all data (pages) are loaded or not
            return false;
        }
    };

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
        mAdapter.setMemasClickListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mAdapter);
        mPresenter.getNews();
        Paginate.with(mRecycler, mCallbacks)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build();
        mRefresh.setOnRefreshListener(() -> {
            mPresenter.refreshNews();
        });
    }

    public void showNews(List<News> news) {
        mAdapter.setData(news);
    }

    @OnClick(R.id.news_add_news)
    public void onAddClick() {
        baseCallback.openAddNews(null);
    }

    //pagination
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
            if (!mAdapter.isLoading()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    mPresenter.getNews();
                }
            }
        }
    };

    @Override
    public void onMemasClick(ArrayList<String> memases) {
        PhotoViewerFragment fragment = PhotoViewerFragment.newInstance(memases);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void setLoading(boolean loading) {
        mRefresh.setRefreshing(loading);
    }
}
