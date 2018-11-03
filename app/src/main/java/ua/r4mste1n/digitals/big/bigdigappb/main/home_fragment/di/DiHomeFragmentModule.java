package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.di;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.main.di.MainScope;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.HomeFragmentModelImpl;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.IHomeFragmentContract;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@Module
public final class DiHomeFragmentModule {

    @Provides
    @MainScope
    final IHomeFragmentContract.Model provideHomeFragmentModel() {
        return new HomeFragmentModelImpl();
    }
}
