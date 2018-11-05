package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.Constants;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainNavigator;
import ua.r4mste1n.digitals.big.bigdigappb.main.service.DataService;
import ua.r4mste1n.digitals.big.bigdigappb.root.GlideApp;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseFragment;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.INetworkManager;

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
    private boolean isTriedLoadPicture = false;

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

    @SuppressWarnings("unchecked")
    @DebugLog
    private void showPicture() {
        showProgress();
        GlideApp.with(getContext())
                .load(mData.getLink())
                .into(target);
    }

    private final BaseTarget target = new BaseTarget<BitmapDrawable>() {
        @Override
        public void onResourceReady(@NonNull final BitmapDrawable _bitmap, final Transition<? super BitmapDrawable> _transition) {
            if (!isTriedLoadPicture) {
                isTriedLoadPicture = true;
                mModel.updateDBWhenPictureLoaded(_bitmap, mData);
            }
            hideProgress();
            ivPicture.setImageBitmap(_bitmap.getBitmap());
        }

        @Override
        public void getSize(final SizeReadyCallback _cb) {
            _cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL);
        }

        @Override
        public void removeCallback(@NonNull final SizeReadyCallback _cb) {

        }

        @Override
        public void onLoadFailed(@Nullable final Drawable _errorDrawable) {
            if (!isTriedLoadPicture) {
                isTriedLoadPicture = true;
                mModel.updateDBWhenLoadPictureFailed(mNetworkManager.isConnected(), mData);
            }
            hideProgress();
            mNavigator.showMessage(getString(R.string.show_picture_error_message));
        }
    };

    @DebugLog
    @Override
    public final void saveLoadedPicture(final BitmapDrawable _bitmap) {
        if (mModel.savePicture(_bitmap.getBitmap()) == null) {
            Toast.makeText(getContext(), R.string.picture_not_saved, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.picture_saved, Toast.LENGTH_SHORT).show();
        }
    }

    @DebugLog
    @Override
    public final void startDeleteDataService() {
        final Intent intent = new Intent(getContext(), DataService.class);
        intent.putExtra(Constants.ServiceKeys.DATA_KEY, mData);
        Objects.requireNonNull(getContext()).startService(intent);
    }

    private void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    private void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }
}
