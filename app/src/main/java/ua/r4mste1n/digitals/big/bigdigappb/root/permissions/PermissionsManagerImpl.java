package ua.r4mste1n.digitals.big.bigdigappb.root.permissions;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import hugo.weaving.DebugLog;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class PermissionsManagerImpl implements IPermissionsManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @DebugLog
    @Override
    public void getPermissions(final Activity _activity, final String[] _permissions, final Callback _callback) {
        final RxPermissions rxPermissions = new RxPermissions(_activity);
        rxPermissions.requestEachCombined(_permissions)
                .subscribe(
                        permission -> {
                            if (permission.granted) _callback.granted();
                            else if (permission.shouldShowRequestPermissionRationale) _callback.deniedWithoutAskNeverAgain();
                            else _callback.deniedWithAskNeverAgain();
                        }
                );
    }

    @DebugLog
    @Override
    public boolean isGranted(final Activity _activity, final String _permission) {
        final RxPermissions rxPermissions = new RxPermissions(_activity);
        return rxPermissions.isGranted(_permission);
    }
}
