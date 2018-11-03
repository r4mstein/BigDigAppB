package ua.r4mste1n.digitals.big.bigdigappb.root.db_manager;

import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public interface IDBManager {
    void insert(PictureData _data);
    void update(PictureData _data);
    int delete(PictureData _data);
}
