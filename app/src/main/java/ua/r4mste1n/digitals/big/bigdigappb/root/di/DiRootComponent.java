package ua.r4mste1n.digitals.big.bigdigappb.root.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.IDBManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.di.DiDBManagerModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.dialog.IDialogShower;
import ua.r4mste1n.digitals.big.bigdigappb.root.dialog.di.DiDialogShowerModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.INetworkManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.network.di.DiNetworkModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.permissions.IPermissionsManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.permissions.di.DiPermissionsModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.IStorageManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.di.DiStorageManagerModule;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@Singleton
@Component(modules = {
        DiAppModule.class,
        DiDBManagerModule.class,
        DiNetworkModule.class,
        DiPermissionsModule.class,
        DiDialogShowerModule.class,
        DiStorageManagerModule.class
})
public interface DiRootComponent {
    Context context();
    IDBManager dbManager();
    INetworkManager networkManager();
    IPermissionsManager permissionsManager();
    IDialogShower dialogShower();
    IStorageManager storageManager();
}
