package ua.r4mste1n.digitals.big.bigdigappb.root.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ua.r4mste1n.digitals.big.bigdigappb.root.ObjectGraph;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public abstract class BaseActivity<N extends INavigator, M extends IModel> extends AppCompatActivity
        implements INavigatorProvider<N>, IPresenter {

    protected N mNavigator;
    @Inject
    protected M mModel;
    protected ObjectGraph mObjectGraph;

    protected abstract void init();

    @Override
    public final N getNavigator() {
        return mNavigator;
    }

    @Override
    protected void onCreate(@Nullable final Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mObjectGraph = ObjectGraph.getInstance(getApplication());
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //noinspection unchecked
        mModel.setPresenter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mModel.removePresenter();
    }

    protected final void bindView(final BaseActivity _activity) {
        ButterKnife.bind(_activity);
    }

    protected final void replaceFragment(final int _containerId, final @NonNull Fragment _fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(_containerId, _fragment)
                .commit();
    }
}
