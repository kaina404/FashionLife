package fashionlife.com.test;

import fashionlife.com.base.component.BasePresenter;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestPresenter extends BasePresenter<TestView> {

    private TestModel model;

    public TestPresenter(TestView view) {
        super(view);
        model = new TestModel();
    }


    public void requestData() {
        model.requestData(new TestModel.TestModelCallBack() {
            @Override
            public void onGetData(String response) {
                if (mView != null) {
                    mView.onSuccess(response);
                }
            }

            @Override
            public void onFailed(String errInfo) {
                if (mView != null) {
                    mView.onFailed(errInfo);
                }
            }

        });
    }

}
