package ua.r4mste1n.digitals.big.bigdigappb.root.db_manager;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import hugo.weaving.DebugLog;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.Constants.ColumnNames;

import static ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.Constants.UriData.URI_LINK;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class DBManager implements IDBManager {

    private final Context mContext;

    public DBManager(final Context _context) {
        mContext = _context;
    }

    @DebugLog
    @Override
    public final void insert(final PictureData _data) {
        final ContentValues values = createContentValues(_data);
        mContext.getContentResolver().insert(URI_LINK, values);
    }

    @DebugLog
    @Override
    public final void update(final PictureData _data) {
        final ContentValues values = createContentValues(_data);
        values.put(ColumnNames.COLUMN_ID, _data.getId());
        final Uri uri = Uri.parse(URI_LINK + "/" + _data.getId());
        mContext.getContentResolver().update(uri, values, null, null);
    }

    @DebugLog
    @Override
    public final int delete(final PictureData _data) {
        final Uri uri = Uri.parse(URI_LINK + "/" + _data.getId());
        return mContext.getContentResolver().delete(uri, null, null);
    }

    @DebugLog
    private ContentValues createContentValues(final PictureData _data) {
        final ContentValues values = new ContentValues();
        values.put(ColumnNames.COLUMN_URL, _data.getLink());
        values.put(ColumnNames.COLUMN_DATE, _data.getTime());
        values.put(ColumnNames.COLUMN_STATUS, _data.getStatus());
        return values;
    }
}
