package ua.r4mste1n.digitals.big.bigdigappb.root.dialog.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.r4mste1n.digitals.big.bigdigappb.root.dialog.DialogShowerImpl;
import ua.r4mste1n.digitals.big.bigdigappb.root.dialog.IDialogShower;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
@Module
public final class DiDialogShowerModule {

    @Provides
    @Singleton
    final IDialogShower provideDialogShower() {
        return new DialogShowerImpl();
    }
}
