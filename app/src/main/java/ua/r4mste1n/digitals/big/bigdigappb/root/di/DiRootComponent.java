package ua.r4mste1n.digitals.big.bigdigappb.root.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@Singleton
@Component(modules = {
        DiAppModule.class
})
public interface DiRootComponent {
    Context context();
}