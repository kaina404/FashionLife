package fashionlife.com.ui.tool;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.base.component.IPresenter;
import fashionlife.com.common.ThreadHelper;
import fashionlife.com.common.UrlConstant;
import fashionlife.com.manager.PermissionMangerHelper;
import fashionlife.com.manager.StartManager;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.ShituUtils;
import fashionlife.com.util.Utils;
import fashionlife.com.util.image.ImageLoadHelper;
import fashionlife.com.widget.AlertUtils;
import fashionlife.com.widget.ProgressDialogHepler;

/**
 * @author Created by lovexujh on 2017/10/9
 */

public class ToolFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mIvHead;
    private TextView selectImage;
    private TextView uploadImage;
    private ImageView imageView;
    private static final String requestURL = "http://image.baidu.com/pictureup/uploadshitu?fr=flash&fm=index&pos=upload";
    private String picPath;

    @Override
    protected IPresenter attachPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIvHead = (ImageView) view.findViewById(R.id.iv_head);
        selectImage = (TextView) view.findViewById(R.id.selectImage);
        uploadImage = (TextView) view.findViewById(R.id.uploadImage);
        selectImage.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        ImageLoadHelper.getFLImageLoader().init(this).loadImage(mIvHead, UrlConstant.TOOL_FRAGMENT_HEAD_IMG_URL);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择图片
            case R.id.selectImage:
                PermissionMangerHelper.checkAllPermission(getActivity());
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, 1);
                break;
            case R.id.uploadImage:
                PermissionMangerHelper.checkAllPermission(getActivity());
                if (picPath == null) {
                    AlertUtils.showMessage(getActivity(), "没有看到图片哦");
                } else {
                    ProgressDialogHepler.show(getActivity(), "", "正在识别...");
                    new ThreadHelper().executorRunnable(new Runnable() {
                        @Override
                        public void run() {
                            String url = ShituUtils.postFile(picPath, requestURL);
                            if (!Utils.isEmpty(url)) {
                                StartManager.startWeb(UrlConstant.BAIDU_SHITU_URL + url, getActivity());
                            }
                            ProgressDialogHepler.dismiss();

                        }
                    });
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                String[] pojo = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContext().getContentResolver().query(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = getActivity().getContentResolver();
                    int colunm_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(colunm_index);

                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        LogUtil.d(this, "uri = " + path);
                        picPath = path;
                        Glide.with(this).load(uri).into(imageView);
                    } else {
                        AlertUtils.showMessage(getActivity(), "没有找到图片哦", "确定", "", null);
                    }
                } else {
                    AlertUtils.showMessage(getActivity(), "没有找到图片哦", "确定", "", null);
                }

            } catch (Exception e) {
                AlertUtils.showMessage(getActivity(), "没有找到图片哦", "确定", "", null);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
