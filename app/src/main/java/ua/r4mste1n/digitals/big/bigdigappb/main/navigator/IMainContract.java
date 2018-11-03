package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import ua.r4mste1n.digitals.big.bigdigappb.root.base.IModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IPresenter;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IMainContract {

    interface Presenter extends IPresenter {

    }

    interface Model extends IModel<Presenter> {

    }
}
