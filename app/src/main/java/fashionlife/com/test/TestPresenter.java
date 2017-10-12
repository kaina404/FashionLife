package fashionlife.com.test;

import fashionlife.com.base.component.AbsBasePresenter;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestPresenter extends AbsBasePresenter<TestView> {

    private TestModel model;
    private TestView view;

    public TestPresenter(TestView view) {
        this.view = view;
        model = new TestModel();
    }

    public void requestData() {
        model.requestData(new TestModel.TestModelCallBack() {
            @Override
            public void onGetData(String response) {
                if (view != null) {
                    view.onSuccess(response);
                }
            }

            @Override
            public void onFailed(String errInfo) {
                if (view != null) {
                    view.onFailed(errInfo);
                }
            }

        });
    }

}
