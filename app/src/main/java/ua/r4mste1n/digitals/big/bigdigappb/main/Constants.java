package ua.r4mste1n.digitals.big.bigdigappb.main;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class Constants {

    public interface Status {
        int LOADED          = 1;
        int ERROR           = 2;
        int UNKNOWN         = 3;
    }

    public interface Value {
        int DEFAULT_VALUE   = -1;
    }

    public interface ServiceKeys {
        String DATA_KEY     = "data_key";
    }
}
