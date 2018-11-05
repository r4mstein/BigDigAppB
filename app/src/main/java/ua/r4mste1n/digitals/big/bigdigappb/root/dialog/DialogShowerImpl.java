package ua.r4mste1n.digitals.big.bigdigappb.root.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import hugo.weaving.DebugLog;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class DialogShowerImpl implements IDialogShower {

    @DebugLog
    @Override
    public void showGenericDialog(final FragmentManager _manager, final Data _data) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_DATA, _data);

        final DialogGeneric dialog = new DialogGeneric();
        dialog.setCancelable(_data.isCancelable());
        dialog.setArguments(bundle);
        dialog.show(_manager, null);
    }
}
