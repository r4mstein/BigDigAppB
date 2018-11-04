package ua.r4mste1n.digitals.big.bigdigappb.root.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import hugo.weaving.DebugLog;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class NetworkManagerImpl implements INetworkManager {

    private final ConnectivityManager mCm;

    @DebugLog
    public NetworkManagerImpl(final Context _context) {
        mCm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public final boolean isConnected() {
        final NetworkInfo activeNetwork = mCm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
