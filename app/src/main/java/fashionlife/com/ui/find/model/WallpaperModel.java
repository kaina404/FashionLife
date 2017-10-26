package fashionlife.com.ui.find.model;

import fashionlife.com.base.data.BaseModel;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.find.data.BingWallpaperBean;
import fashionlife.com.util.JsonHelper;
import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/26
 * @descripition:
 */

public class WallpaperModel extends BaseModel<IWallpaper> implements INetCall {

    public WallpaperModel(IWallpaper iWallpaper) {
        super(iWallpaper);
    }

    public void queryWallpaperUrl(){
        // TODO: 2017/10/26  如果已经存在图片不下载
        NetManager.queryBingWallpaperUrl(this);
    }

    @Override
    public void onResponse(int requestId, String response) {
        switch (requestId){
            case NetId.QUERY_BING_WALLPAPER_URL:
                parseBingWallpaper(response);
                break;
            case NetId.DOWNLOAD_WALLPAPER:
                if(mModel != null){
                    mModel.downloadImgSucceed(response);
                }
                break;
        }
    }

    private void parseBingWallpaper(String response) {
        BingWallpaperBean bean = JsonHelper.parseObject(response, BingWallpaperBean.class);
        String url = bean.getImages().get(0).getUrl();
        if(!Utils.isEmpty(url)){
            NetManager.downloadImg(NetId.DOWNLOAD_WALLPAPER, "http://www.bing.com" + url, this);
        }
    }

    @Override
    public void onFailure(int requestId, String errObj) {

    }
}
