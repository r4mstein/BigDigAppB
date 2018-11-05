package ua.r4mste1n.digitals.big.bigdigappb.root.permissions;

import android.app.Activity;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public interface IPermissionsManager {
    void getPermissions(Activity activity, String[] permissions, Callback callback);
    boolean isGranted(Activity activity, String permission);

    interface Callback {
        void granted();
        void deniedWithoutAskNeverAgain();
        void deniedWithAskNeverAgain();
    }
}
