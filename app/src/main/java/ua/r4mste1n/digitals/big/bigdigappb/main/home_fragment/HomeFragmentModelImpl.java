package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.BaseModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.IDBManager;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class HomeFragmentModelImpl extends BaseModel<IHomeFragmentContract.Presenter>
        implements IHomeFragmentContract.Model {

    private final IDBManager mDBManager;

    @Inject
    public HomeFragmentModelImpl(final IDBManager _dbManager) {
        mDBManager = _dbManager;
    }

    @DebugLog
    @Override
    public final void insertData(final PictureData _data) {
        mDBManager.insert(_data);
    }

    @DebugLog
    @Override
    public final void updateData(final PictureData _data) {
        mDBManager.update(_data);
    }

    @DebugLog
    @Override
    public final void deleteData(final PictureData _data) {
        mDBManager.delete(_data);
    }
}
