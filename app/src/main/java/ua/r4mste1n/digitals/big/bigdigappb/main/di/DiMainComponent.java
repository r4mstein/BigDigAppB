package ua.r4mste1n.digitals.big.bigdigappb.main.di;

import dagger.Component;
import ua.r4mste1n.digitals.big.bigdigappb.main.navigator.MainActivity;
import ua.r4mste1n.digitals.big.bigdigappb.root.di.DiRootComponent;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
@MainScope
@Component(modules = {
        DiMainModule.class,
},
        dependencies = {DiRootComponent.class})
public interface DiMainComponent {
    void inject(MainActivity _activity);
}
