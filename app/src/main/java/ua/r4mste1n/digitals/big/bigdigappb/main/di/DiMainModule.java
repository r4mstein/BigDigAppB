package ua.r4mste1n.digitals.big.bigdigappb.main.di;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.IMainContract;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.MainModelImpl;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@Module
public final class DiMainModule {

    @Provides
    @MainScope
    final IMainContract.Model provideMainModel() {
        return new MainModelImpl();
    }
}
