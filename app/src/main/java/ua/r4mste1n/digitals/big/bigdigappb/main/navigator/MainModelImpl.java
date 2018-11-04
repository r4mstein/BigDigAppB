package ua.r4mste1n.digitals.big.bigdigappb.main.navigator;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseModel;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class MainModelImpl extends BaseModel<IMainContract.Presenter> implements IMainContract.Model {

    private Disposable mTimeDisposable;

    @Inject
    public MainModelImpl() {

    }

    @DebugLog
    @Override
    public final void startTimer() {
        mTimeDisposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> mPresenter.refreshTime(time));
    }

    @DebugLog
    @Override
    public final void stopTimer() {
        if (mTimeDisposable != null && !mTimeDisposable.isDisposed()) mTimeDisposable.dispose();
    }
}
