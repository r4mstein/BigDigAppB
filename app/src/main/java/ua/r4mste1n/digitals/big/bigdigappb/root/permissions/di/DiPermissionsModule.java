package ua.r4mste1n.digitals.big.bigdigappb.root.permissions.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.root.permissions.IPermissionsManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.permissions.PermissionsManagerImpl;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
@Module
public final class DiPermissionsModule {

    @Provides
    @Singleton
    final IPermissionsManager providePermissionsManager() {
        return new PermissionsManagerImpl();
    }
}
