package ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.IStorageManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.StorageManager;

/**
 * Created by Alex Shtain on 05.11.2018.
 */
@Module
public final class DiStorageManagerModule {

    @Provides
    @Singleton
    final IStorageManager provideStorageManager() {
        return new StorageManager();
    }
}
