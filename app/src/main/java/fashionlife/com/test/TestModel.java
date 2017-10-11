package fashionlife.com.test;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestModel {

    public void reqeustData(String params, TestModelCallBack callBack){
        if(true){
            callBack.onGetData();
        }else {
            callBack.onFailed();
        }
    }

    public interface TestModelCallBack{
        void onGetData();
        void onFailed();
    }
}
