package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.INavigator;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IMainNavigator extends INavigator {
    void showHomeFragment(PictureData _data);

    void showMessage(String _message);
}
