package ua.r4mste1n.digitals.big.bigdigappb.root.storage_manager;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

import hugo.weaving.DebugLog;

/**
 * Created by Alex Shtain on 05.11.2018.
 */
public final class StorageManager implements IStorageManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @DebugLog
    @Override
    public final String savePicture(final Bitmap _bitmap) {
        File file;
        final String filePath = getFilePath();
        if (filePath == null) return null;
        else file = new File(filePath);
        try {
            file.createNewFile();
            final FileOutputStream ostream = new FileOutputStream(file);
            _bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    @DebugLog
    private String getFilePath() {
        File file;
        if (isExternalStorageWritable()) {
            file = new File(Environment.getExternalStorageDirectory().getPath(), "/BIGDIG/test/B");
        } else {
            return null;
        }
        if (!file.mkdirs()) Log.d("StorageManager", "Directory not created");
        return file.getAbsolutePath() + "/" + createFileName() + ".JPEG";
    }

    @DebugLog
    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    @DebugLog
    private String createFileName() {
        return new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss", Locale.US).format(System.currentTimeMillis());
    }
}
