package ua.r4mste1n.digitals.big.bigdigappb.root.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.r4mste1n.digitals.big.bigdigappb.R;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class DialogGeneric extends DialogFragment {
    @BindView(R.id.tvTitle_DG)
    TextView tvTitle;
    @BindView(R.id.tvMessage_DG)
    TextView tvMessage;
    @BindView(R.id.btnPositive_DG)
    TextView btnPositive;
    @BindView(R.id.btnNegative_DG)
    TextView btnNegative;

    private Unbinder mUnbinder;

    @NonNull
    @Override
    public View onCreateView(final LayoutInflater _inflater, @Nullable final ViewGroup _container, @Nullable final Bundle _savedInstanceState) {
        final View view =_inflater.inflate(R.layout.dialog_generic, _container, false);
        setupDialog();
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View _view, @Nullable final Bundle _savedInstanceState) {
        super.onViewCreated(_view, _savedInstanceState);
        fillUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setupDialog() {
        //noinspection ConstantConditions
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

    private void fillUI() {
        IDialogShower.Data data = null;
        if (getArguments() != null) data = (IDialogShower.Data) getArguments().getSerializable(IDialogShower.KEY_DATA);
        if (data != null) {
            tvTitle.setText(data.getTitle());
            tvMessage.setText(data.getMessage());
            setupButtons(data);
        }
    }

    private void setupButtons(final IDialogShower.Data _data) {
        final int code = _data.getCode();
        final IDialogShower.DialogListener dialogListener = (IDialogShower.DialogListener)
                (getParentFragment() == null ? getActivity() : getParentFragment());

        if (_data.isPositiveButtonEnabled()) {
            if (!TextUtils.isEmpty(_data.getPositiveButtonText())) btnPositive.setText(_data.getPositiveButtonText());
            btnPositive.setOnClickListener((View v) -> {
                if (dialogListener != null) dialogListener.onDialogClick(IDialogShower.DialogButton.POSITIVE, code);
                dismiss();
            });
        }

        if (_data.isNegativeButtonEnabled()) {
            btnNegative.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(_data.getNegativeButtonText())) btnNegative.setText(_data.getNegativeButtonText());
            btnNegative.setOnClickListener((View v) -> {
                if (dialogListener != null) dialogListener.onDialogClick(IDialogShower.DialogButton.NEGATIVE, code);
                dismiss();
            });
        }
    }
}
