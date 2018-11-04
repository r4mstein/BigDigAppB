package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import ua.r4mste1n.digitals.big.bigdigappb.root.base.IModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IPresenter;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IMainContract {

    interface Presenter extends IPresenter {
        void refreshTime(long time);
    }

    interface Model extends IModel<Presenter> {
        void startTimer();
        void stopTimer();
    }

    enum State {
        FROM_APP_A, FROM_LAUNCHER
    }
}
