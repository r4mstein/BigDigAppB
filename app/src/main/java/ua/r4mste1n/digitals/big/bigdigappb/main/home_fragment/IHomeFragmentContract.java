package ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IModel;
import ua.r4mste1n.digitals.big.bigdigappb.root.base.IPresenter;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IHomeFragmentContract {

    interface Presenter extends IPresenter {
        void saveLoadedPicture(final BitmapDrawable _bitmap);
        void startDeleteDataService();
    }

    interface Model extends IModel<Presenter> {
        String savePicture(Bitmap _bitmap);
        void updateDBWhenLoadPictureFailed(final boolean _isConnected, final PictureData _data);
        void updateDBWhenPictureLoaded(final BitmapDrawable _bitmap, final PictureData _data);
    }
}
