package ua.r4mste1n.digitals.big.bigdigappb.root.dialog;

import android.support.v4.app.FragmentManager;

import java.io.Serializable;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public interface IDialogShower {
    int NO_CODE = -1;
    String KEY_DATA = "key_data";

    void showGenericDialog(FragmentManager _manager, Data _data);

    enum DialogButton {POSITIVE, NEGATIVE}

    interface DialogListener {
        void onDialogClick(DialogButton _button, int _code);
    }

    final class Data implements Serializable {
        private String mTitle = null;
        private String mMessage = null;
        private boolean mPositiveButtonEnabled = true;
        private String mPositiveButtonText = null;
        private boolean mNegativeButtonEnabled = false;
        private String mNegativeButtonText = null;
        private int mCode = NO_CODE;
        private boolean mCancelable = true;

        public final Data setTitle(final String _title) {
            mTitle = _title;
            return this;
        }

        public final String getTitle() {
            return mTitle;
        }

        public final Data setMessage(final String _message) {
            mMessage = _message;
            return this;
        }

        public final String getMessage() {
            return mMessage;
        }

        public final boolean isPositiveButtonEnabled() {
            return mPositiveButtonEnabled;
        }

        public final Data setPositiveButtonEnabled(final boolean _positiveButtonEnabled) {
            mPositiveButtonEnabled = _positiveButtonEnabled;
            return this;
        }

        public final Data setPositiveButtonText(final String _positiveButtonText) {
            mPositiveButtonText = _positiveButtonText;
            return this;
        }

        public final String getPositiveButtonText() {
            return mPositiveButtonText;
        }

        public final boolean isNegativeButtonEnabled() {
            return mNegativeButtonEnabled;
        }

        public final Data setNegativeButtonEnabled(final boolean _negativeButtonEnabled) {
            mNegativeButtonEnabled = _negativeButtonEnabled;
            return this;
        }

        public final Data setNegativeButtonText(final String _negativeButtonText) {
            mNegativeButtonText = _negativeButtonText;
            return this;
        }

        public final String getNegativeButtonText() {
            return mNegativeButtonText;
        }

        public final Data setCode(final int _code) {
            mCode = _code;
            return this;
        }

        public final int getCode() {
            return mCode;
        }

        public final Data setCancelable(final boolean _cancelable) {
            mCancelable = _cancelable;
            return this;
        }

        public final boolean isCancelable() {
            return mCancelable;
        }
    }
}
