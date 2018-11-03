package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainNavigator;
import ua.r4mste1n.digitals.big.bigdigappb.root.GlideApp;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseFragment;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class HomeFragment extends BaseFragment<IMainNavigator, IHomeFragmentContract.Model>
        implements IHomeFragmentContract.Presenter {

    @BindView(R.id.ivPicture_HF)
    ImageView ivPicture;

    public static final String PICTURE_DATA_KEY = "picture_data_key";

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
        GlideApp.with(getContext())
                .load(mData.getLink())
                .error(R.drawable.ic_launcher_background)
                .into(ivPicture);
    }
}
