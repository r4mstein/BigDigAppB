package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.HomeFragment;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainContract.State;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseActivity;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.Constants.ColumnNames;
import ua.r4mste1n.digitals.big.bigdigappb.root.permissions.IPermissionsManager;

import static ua.r4mste1n.digitals.big.bigdigappb.main.Constants.Value.DEFAULT_VALUE;

public final class MainActivity extends BaseActivity<IMainNavigator, IMainContract.Model>
        implements IMainContract.Presenter, IMainNavigator {

    private static final String ACTION       = "ua.r4mste1n.digitals.big.bigdigappb.LAUNCH";
    private static final int START_TIME      = 10;

    @BindView(R.id.toolbar_AH)
    Toolbar toolbar;
    @BindView(R.id.rlMessageContainer_AM)
    RelativeLayout rlMessageContainer;
    @BindView(R.id.tvMessage_AM)
    TextView tvMessage;
    @BindView(R.id.tvTime_AM)
    TextView tvTime;

    @Inject
    IPermissionsManager mPermissionsManager;
    private String[] mPermissions = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private State mState;

    @DebugLog
    @Override
    protected void init() {
        mNavigator = this;
        mObjectGraph.getMainComponent().inject(this);
    }

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView(this);
        setupUI();
        checkPermissions();
    }

    @DebugLog
    @Override
    protected void onRestart() {
        super.onRestart();
        checkPermissions();
    }

    @DebugLog
    @Override
    protected void onStop() {
        super.onStop();
        mModel.stopTimer();
        if (mState != null && mState == State.FROM_LAUNCHER) tvTime.setText("");
    }

    @DebugLog
    private void checkPermissions() {
        if (mPermissionsManager.isGranted(this, mPermissions[0])) {
            setupIntentData(getIntent());
        } else {
            mPermissionsManager.getPermissions(this, mPermissions, new IPermissionsManager.Callback() {
                @Override
                public void granted() {
                    setupIntentData(getIntent());
                }

                @Override
                public void deniedWithoutAskNeverAgain() {
                    Toast.makeText(MainActivity.this, R.string.error_permissions, Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void deniedWithAskNeverAgain() {
                    showPermissionDeniedDialog();
                }
            });
        }
    }

    @DebugLog
    private void setupIntentData(final Intent _intent) {
        if (_intent.getAction() != null && _intent.getAction().equals(ACTION)) {
            mState = State.FROM_APP_A;
            if (getSupportFragmentManager().getFragments().size() > 0) return;

            final PictureData data = new PictureData();
            data.setId(_intent.getLongExtra(ColumnNames.COLUMN_ID, DEFAULT_VALUE));
            data.setLink(_intent.getStringExtra(ColumnNames.COLUMN_URL));
            data.setStatus(_intent.getIntExtra(ColumnNames.COLUMN_STATUS, DEFAULT_VALUE));
            data.setTime(_intent.getLongExtra(ColumnNames.COLUMN_DATE, DEFAULT_VALUE));
            showHomeFragment(data);
        } else {
            mState = State.FROM_LAUNCHER;
            tvMessage.setText(R.string.close_message);
            tvTime.setVisibility(View.VISIBLE);
            rlMessageContainer.setVisibility(View.VISIBLE);
            mModel.startTimer();
        }
    }

    @DebugLog
    private void setupUI() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @DebugLog
    @Override
    public final void showHomeFragment(final PictureData _data) {
        replaceFragment(R.id.flRootContainer_AM, HomeFragment.newInstance(_data));
    }

    @DebugLog
    @Override
    public final void showMessage(final String _message) {
        tvMessage.setText(_message);
        rlMessageContainer.setVisibility(View.VISIBLE);
    }

    @DebugLog
    @Override
    public final void refreshTime(final long time) {
        if (time == START_TIME) finish();
        tvTime.setText(String.valueOf(START_TIME - time));
    }
}
