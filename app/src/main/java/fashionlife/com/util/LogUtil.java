package fashionlife.com.util;

/**
 * Created by lovexujh on 2017/10/9
 */

public class LogUtil {
    private static final String sTag = "FashionLife";
    private static final boolean debug = true;

    public static void d(String msg, Object... params) {
        if (debug) {
            android.util.Log.d(sTag, String.format(msg, params));
        }
    }
}
