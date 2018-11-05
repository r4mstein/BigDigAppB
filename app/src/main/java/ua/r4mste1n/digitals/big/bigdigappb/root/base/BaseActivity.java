package ua.r4mste1n.digitals.big.bigdigappb.root.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.root.ObjectGraph;
import ua.r4mste1n.digitals.big.bigdigappb.root.dialog.IDialogShower;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public abstract class BaseActivity<N extends INavigator, M extends IModel> extends AppCompatActivity
        implements INavigatorProvider<N>, IPresenter, IDialogShower.DialogListener {

    protected static final int DLG_CODE_PERMISSION_DENIED = 555;

    protected N mNavigator;
    @Inject
    protected M mModel;
    protected ObjectGraph mObjectGraph;
    @Inject
    protected IDialogShower mDialogShower;

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

    @DebugLog
    protected final void showPermissionDeniedDialog() {
        mDialogShower.showGenericDialog(getSupportFragmentManager(),
                new IDialogShower.Data()
                        .setTitle("Error")
                        .setMessage(getString(R.string.error_permission_message))
                        .setPositiveButtonText(getString(R.string.settings))
                        .setNegativeButtonEnabled(true)
                        .setCancelable(false)
                        .setCode(DLG_CODE_PERMISSION_DENIED));
    }

    @DebugLog
    protected final void onClickPermissionDeniedDialogSettingsBtn() {
        final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        final Uri uri = Uri.fromParts("package", Objects.requireNonNull(this).getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @DebugLog
    protected void onClickPermissionDeniedDialogCancelBtn() {
        Toast.makeText(this, R.string.error_permissions, Toast.LENGTH_SHORT).show();
        finish();
    }

    @CallSuper
    @DebugLog
    @Override
    public void onDialogClick(final IDialogShower.DialogButton _button, final int _code) {
        if (DLG_CODE_PERMISSION_DENIED == _code && IDialogShower.DialogButton.POSITIVE == _button) {
            onClickPermissionDeniedDialogSettingsBtn();
        } else if (DLG_CODE_PERMISSION_DENIED == _code && IDialogShower.DialogButton.NEGATIVE == _button) {
            onClickPermissionDeniedDialogCancelBtn();
        }
    }
}
