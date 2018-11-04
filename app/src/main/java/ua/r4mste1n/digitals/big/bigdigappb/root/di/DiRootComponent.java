package ua.r4mste1n.digitals.big.bigdigappb.root.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.IDBManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.di.DiDBManagerModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.INetworkManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.di.DiNetworkModule;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@Singleton
@Component(modules = {
        DiAppModule.class,
        DiDBManagerModule.class,
        DiNetworkModule.class
})
public interface DiRootComponent {
    Context context();
    IDBManager dbManager();
    INetworkManager networkManager();
}
