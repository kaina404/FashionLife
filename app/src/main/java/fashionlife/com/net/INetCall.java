package fashionlife.com.net;

/**
 * Created by lovexujh on 2017/10/9
 */

public interface INetCall {
    void onResponse(int requestId, String response);

    void onFailure(int requestId, String errObj);
}
