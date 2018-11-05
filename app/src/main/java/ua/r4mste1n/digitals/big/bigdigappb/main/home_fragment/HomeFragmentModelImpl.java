package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.IDBManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager.IStorageManager;

import static ua.r4mste1n.digitals.big.bigdigappb.main.Constants.Status;
import static ua.r4mste1n.digitals.big.bigdigappb.main.Constants.Value.DEFAULT_VALUE;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class HomeFragmentModelImpl extends BaseModel<IHomeFragmentContract.Presenter>
        implements IHomeFragmentContract.Model {

    private final IDBManager mDBManager;
    private final IStorageManager mStorageManager;

    @Inject
    public HomeFragmentModelImpl(final IDBManager _dbManager,
                                 final IStorageManager _storageManager) {
        mDBManager = _dbManager;
        mStorageManager = _storageManager;
    }

    @DebugLog
    @Override
    public final String savePicture(final Bitmap _bitmap) {
        return mStorageManager.savePicture(_bitmap);
    }

    @DebugLog
    @Override
    public final void updateDBWhenLoadPictureFailed(final boolean _isConnected, final PictureData _data) {
        switch (_data.getStatus()) {
            case DEFAULT_VALUE:
                if (_isConnected) setupAndInsertNewData(Status.ERROR, _data);
                else setupAndInsertNewData(Status.UNKNOWN, _data);
                break;
            case Status.ERROR:
                if (!_isConnected) setupAndUpdateNewData(Status.UNKNOWN, _data);
                break;
            case Status.UNKNOWN:
                if (_isConnected) setupAndUpdateNewData(Status.ERROR, _data);
                break;
        }
    }

    @DebugLog
    @Override
    public final void updateDBWhenPictureLoaded(final BitmapDrawable _bitmap, final PictureData _data) {
        switch (_data.getStatus()) {
            case DEFAULT_VALUE:
                setupAndInsertNewData(Status.LOADED, _data);
                break;
            case Status.LOADED:
                mPresenter.saveLoadedPicture(_bitmap);
                mPresenter.startDeleteDataService();
                break;
            case Status.ERROR:
            case Status.UNKNOWN:
                setupAndUpdateNewData(Status.LOADED, _data);
                break;
        }
    }

    @DebugLog
    private void setupAndUpdateNewData(final int _status, final PictureData _data) {
        _data.setStatus(_status);
        mDBManager.update(_data);
    }

    @DebugLog
    private void setupAndInsertNewData(final int _status, final PictureData _data) {
        _data.setTime(System.currentTimeMillis());
        _data.setStatus(_status);
        mDBManager.insert(_data);
    }
}
