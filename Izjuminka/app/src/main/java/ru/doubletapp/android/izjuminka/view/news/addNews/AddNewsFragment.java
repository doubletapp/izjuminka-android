package ru.doubletapp.android.izjuminka.view.news.addNews;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.presenter.news.AddNewsPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;
import ru.yandex.speechkit.gui.RecognizerActivity;

import static android.app.Activity.RESULT_OK;
import static ru.doubletapp.android.izjuminka.view.news.addNews.AddNewsActivity.KEY_NEWS;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class AddNewsFragment extends BaseFragment<AddNewsPresenter> {

    public static final String TAG = AddNewsFragment.class.getSimpleName();
    private static final int PERMISSION_STORE_REQUEST_CODE = 0;
    private static final int PERMISSION_LOCATION_REQUEST_CODE = 1;
    private static final int REQUEST_CODE_SPEECH_RECOGNIZER = 11;

    @BindView(R.id.add_news_location)
    CheckBox mLocationChecker;
    @BindView(R.id.add_news_description)
    EditText mDescription;
    @BindView(R.id.add_news_title)
    EditText mTitle;
    @BindView(R.id.add_news_link_container)
    LinearLayout mLinkContainer;
    @BindView(R.id.news_collapsed_description)
    TextView mLinkDescription;
    @BindView(R.id.news_collapsed_image)
    ImageView mLinkImage;
    @BindView(R.id.add_news_recycler)
    RecyclerView mRecycler;
    private AddImageAdapter mAdapter;
    @Nullable
    private Location mLocation;
    @Nullable
    private ProgressDialog mDialog;


    public static AddNewsFragment newInstance() {

        Bundle args = new Bundle();

        AddNewsFragment fragment = new AddNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static AddNewsFragment newInstance(News news) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_NEWS, news);
        AddNewsFragment fragment = new AddNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    protected AddNewsPresenter createPresenter() {
        return IzjuminkaApplication.getApplicationComponent(getContext())
                .getAddNewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_news, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mAdapter = new AddImageAdapter(getContext(), () -> {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_STORE_REQUEST_CODE);
            } else {
                showSelectDialog();
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORE_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showSelectDialog();
                }
                break;
            case PERMISSION_LOCATION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation();
                }
                break;
        }
    }

    private void showSelectDialog() {
        PickImageDialog.build(new PickSetup().setSystemDialog(true))
                .setOnPickResult(r -> mAdapter.addImage(r.getPath())).show(getChildFragmentManager());
    }

    @OnClick(R.id.add_news_location)
    void onLocationClick() {
        /*if (!mLocationChecker.isChecked()) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_CODE);
            } else {
                requestLocation();
            }
        }
        mLocationChecker.setChecked(false);*/
        //stub!!!
    }

    @OnClick(R.id.add_news_speechkit)
    void recognizeSpeech() {
        startActivityForResult(new Intent(getContext(), RecognizerActivity.class), REQUEST_CODE_SPEECH_RECOGNIZER);
    }

    private void requestLocation() {
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                //Got the location!
                Log.d(TAG, "gotLocation: " + location.getLatitude() + ":" + location.getLongitude());
                mLocation = location;
                mLocationChecker.setChecked(true);
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(getContext(), locationResult);
    }

    public void onNextClick() {
        mPresenter.onNextClick(mTitle.getText().toString(),
                mDescription.getText().toString(),
                mAdapter.getItems(),
                mLocation);
    }

    public void showLinkedNews(News news) {
        mLinkContainer.setVisibility(View.VISIBLE);

        if (!news.getImages().isEmpty())
            Glide.with(getContext())
                    .load(news.getImages().get(0))
                    .apply(RequestOptions.centerCropTransform())
                    .into(mLinkImage);
        mLinkDescription.setText(Html.fromHtml(news.getDescription()));
    }

    public void showLoading() {
        mDialog = ProgressDialog.show(getContext(), "",
                getString(R.string.add_news_loading), true);
    }

    public void hideLoading() {
        if (mDialog != null)
            mDialog.hide();
    }

    public void close() {
        getActivity().onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SPEECH_RECOGNIZER && resultCode == RecognizerActivity.RESULT_OK) {
            String result = data.getStringExtra(RecognizerActivity.EXTRA_RESULT);
            mDescription.setText(result);
        }
    }
}
