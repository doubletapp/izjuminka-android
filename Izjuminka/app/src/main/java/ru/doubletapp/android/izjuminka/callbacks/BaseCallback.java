package ru.doubletapp.android.izjuminka.callbacks;

import android.support.annotation.Nullable;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public interface BaseCallback {

    void setTitle(String title);

    void openAddNews(@Nullable News news);
}
