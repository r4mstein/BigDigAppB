package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.Constants.Status;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainNavigator;
import ua.r4mste1n.digitals.big.bigdigappb.root.GlideApp;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseFragment;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.INetworkManager;

import static ua.r4mste1n.digitals.big.bigdigappb.main.Constants.Value.DEFAULT_VALUE;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class HomeFragment extends BaseFragment<IMainNavigator, IHomeFragmentContract.Model>
        implements IHomeFragmentContract.Presenter {

    @BindView(R.id.ivPicture_HF)
    ImageView ivPicture;
    @BindView(R.id.pbLoader_HF)
    ProgressBar pbLoader;

    private static final String PICTURE_DATA_KEY = "picture_data_key";

    @Inject
    INetworkManager mNetworkManager;
    private PictureData mData;

    @DebugLog
    public static HomeFragment newInstance(final PictureData _data) {
        final HomeFragment fragment = new HomeFragment();
        final Bundle bundle = new Bundle();
        bundle.putSerializable(PICTURE_DATA_KEY, _data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @DebugLog
    @Override
    protected void init() {
        mObjectGraph.getMainComponent().inject(this);
    }

    @DebugLog
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        final View view = _inflater.inflate(R.layout.home_fragment, _container, false);
        if (getArguments() != null && getArguments().containsKey(PICTURE_DATA_KEY)) {
            mData = (PictureData) getArguments().getSerializable(PICTURE_DATA_KEY);
        }
        bindView(this, view);
        return view;
    }

    @DebugLog
    @Override
    public void onStart() {
        super.onStart();
        showPicture();
    }

    @DebugLog
    private void showPicture() {
        showProgress();
        GlideApp.with(getContext())
                .load(mData.getLink())
                .listener(mGlideListener)
                .into(ivPicture);
    }

    private final RequestListener<Drawable> mGlideListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            hideProgress();
            mNavigator.showMessage(getString(R.string.show_picture_error_message));
            updateDBWhenLoadPictureFailed(mNetworkManager.isConnected());
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            hideProgress();
            updateDBWhenPictureLoaded();
            return false;
        }
    };

    @DebugLog
    private void updateDBWhenLoadPictureFailed(final boolean _isConnected) {
        switch (mData.getStatus()) {
            case DEFAULT_VALUE:
                if (_isConnected) setupAndInsertNewData(Status.ERROR);
                else setupAndInsertNewData(Status.UNKNOWN);
                break;
            case Status.ERROR:
                if (!_isConnected) setupAndUpdateNewData(Status.UNKNOWN);
                break;
            case Status.UNKNOWN:
                if (_isConnected) setupAndUpdateNewData(Status.ERROR);
                break;
        }
    }

    @DebugLog
    private void updateDBWhenPictureLoaded() {
        switch (mData.getStatus()) {
            case DEFAULT_VALUE:
                setupAndInsertNewData(Status.LOADED);
                break;
            case Status.LOADED:
                mModel.deleteData(mData);
                break;
            case Status.ERROR:
            case Status.UNKNOWN:
                setupAndUpdateNewData(Status.LOADED);
                break;
        }
    }

    @DebugLog
    private void setupAndUpdateNewData(final int _status) {
        mData.setStatus(_status);
        mModel.updateData(mData);
    }

    @DebugLog
    private void setupAndInsertNewData(final int _status) {
        mData.setTime(System.currentTimeMillis());
        mData.setStatus(_status);
        mModel.insertData(mData);
    }

    private void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    private void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }
}
