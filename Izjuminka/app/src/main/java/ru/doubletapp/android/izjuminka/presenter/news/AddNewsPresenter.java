package ru.doubletapp.android.izjuminka.presenter.news;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.news.addNews.AddNewsFragment;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class AddNewsPresenter extends BasePresenter<AddNewsFragment> {

    @Override
    public void attachView(@NonNull AddNewsFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);
    }

    public void onNextClick(String title, String description, @Nullable List<String> items, @Nullable Location mLocation) {
        
    }
}
