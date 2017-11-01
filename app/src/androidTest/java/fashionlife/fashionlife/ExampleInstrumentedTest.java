package fashionlife.fashionlife;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fashionlife.db.DBCache;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("fashionlife.com", appContext.getPackageName());
    }

    @Test
    public void testCreateDB(){
        DBCache DBCache = new DBCache();
//        LogUtil.d("AndroidJUnit4", "AndroidJUnit4=======");
    }
}
