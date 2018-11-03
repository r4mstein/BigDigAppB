package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IPresenter;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IHomeFragmentContract {

    interface Presenter extends IPresenter {

    }

    interface Model extends IModel<Presenter> {
        void insertData(PictureData _data);
        void updateData(PictureData _data);
        void deleteData(PictureData _data);
    }
}
