package ua.r4mste1n.digitals.big.bigdigappb.root.base;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface INavigatorProvider<N extends INavigator> {
    N getNavigator();
}
