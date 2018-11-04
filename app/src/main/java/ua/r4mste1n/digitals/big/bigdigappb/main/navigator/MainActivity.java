package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.HomeFragment;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainContract.State;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseActivity;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.Constants.ColumnNames;

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
        setupIntentData(getIntent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mState != null && mState == State.FROM_LAUNCHER) mModel.startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mModel.stopTimer();
        if (mState != null && mState == State.FROM_LAUNCHER) tvTime.setText("");
    }

    @DebugLog
    private void setupIntentData(final Intent _intent) {
        if (_intent.getAction() != null && _intent.getAction().equals(ACTION)) {
            mState = State.FROM_APP_A;
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
