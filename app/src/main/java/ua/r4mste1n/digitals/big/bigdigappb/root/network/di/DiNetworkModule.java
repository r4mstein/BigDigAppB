package ua.r4mste1n.digitals.big.bigdigappb.root.network.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.INetworkManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.NetworkManagerImpl;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
@Module
public final class DiNetworkModule {

    @Provides
    @Singleton
    final INetworkManager provideNetworkManager(final Context context) {
        return new NetworkManagerImpl(context);
    }
}
