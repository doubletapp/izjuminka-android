package ru.doubletapp.android.izjuminka.api.news;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.annotation.NonNull;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsRemoteRepository implements NewsRetrofit {

    @NonNull
    private final NewsRetrofit mNewsRetrofit;

    NewsRemoteRepository(@NonNull NewsRetrofit newsRetrofit) {
        mNewsRetrofit = newsRetrofit;
    }

    @Override
    public List<News> getNews() {

        // TODO: 21.10.2017 mock
        List<News> result = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            result.add(new News(Arrays.asList("http://kot-pes.com/wp-content/uploads/2015/06/Shiba-Inu.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWXK8lx5fwMqPI5U1WMY6RsPpz3Smt0RlG1w7QNEkCLAF2JAwI",
                    "http://kakbik.ru/wp-content/uploads/2014/07/mediczona-izum.jpg",
                    "http://brjunetka.ru/wp-content/uploads/2014/07/Temnyiy-i-svetlyiy-vid.jpg",
                    "http://povarusha.ru/foto/izum.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgtx_OVPk2hgIhAZ7KU_tanMEEgNIzfbmS5k7iHY959Mb31g6q"),
                    "Давайте исходить из очевидных вещей: Ксения Собчак @xenia_sobchak не рассчитывает стать президентом РФ. Она так и говорит. Но она говорит еще вот что: голосуя за меня, вы голосуете «против всех», то есть вы даете знать власти и прежде всего президенту Путину, что вас не устраивает нынешнее положение дел, что нет для вас подходящего кандидата. Допустим, что за эту кандидатуру – Собчак/Против всех – проголосует 25% избирателей, то есть больше, чем за все партии, кроме Путина. Это важный сигнал. Это реальное выражение того, что четверть избирателей недовольны положением дел. И это игнорировать нельзя. Выдвинув свою кандидатуру таким образом, Ксения Собчак поступила смело. Она понимала, что все кому не лень осудят ее по самым разным направлениям. То есть она подставилась под удар. Для этого требуется некоторая отвага. Я поддерживаю ее и призываю всех, кто считают, что и в самом деле не за кого голосовать, проголосовать за кандидата «против всех». Важно, чтобы власть понимала, что многие недовольны положением вещей. #познер #собчак #россия #президент #выборы"));
        }
        return result;
    }
}
