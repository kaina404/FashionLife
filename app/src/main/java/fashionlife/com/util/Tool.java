package fashionlife.com.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import fashionlife.com.app.APPConstant;
import fashionlife.com.listener.ProgressResponseListener;

/**
 * Created by lovexujh on 2017/9/19
 */

public class Tool {

    public static boolean isEmpty(List urls) {
        return urls == null || urls.isEmpty();
    }


    public static String Base64(String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            paramString = null;
        }
        try {
            byte[] arrayOfByte = paramString.getBytes("UTF-8");

            paramString = new String(Base64.encode(arrayOfByte, 0, arrayOfByte.length, 2), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            paramString = null;
        }
        return paramString;
    }

    public static String getCacheDir() {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            path = Environment.getDownloadCacheDirectory().getAbsolutePath();
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return path;
    }

    public static boolean inputStream2File(InputStream ins, File file, ProgressResponseListener progressListener, double all) {
        FileOutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        if (os == null) {
            return false;
        }
        int bytesRead;
        byte[] buffer = new byte[8192];
        try {
            if (progressListener == null) {
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } else {
                double read = 0;//已经写入的
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                    read = read + bytesRead;
                    progressListener.onResponseProgress(read, all);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return true;
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static BigDecimal divide(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, 10, BigDecimal.ROUND_HALF_DOWN);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 相片按相框的比例动态缩放
     *
     * @param bmp       要缩放的图片
     * @param newWidth  模板宽度
     * @param newHeight 模板高度
     * @return
     */
    public static Bitmap upImageSize(Bitmap bmp, int newWidth, int newHeight) {
        if (bmp == null) {
            return null;
        }
        // 计算比例
        float scaleX = (float) newWidth / bmp.getWidth();// 宽的比例
        float scaleY = (float) newHeight / bmp.getHeight();// 高的比例
        //新的宽高
        int newW = 0;
        int newH = 0;
        if (scaleX > scaleY) {
            newW = (int) (bmp.getWidth() * scaleX);
            newH = (int) (bmp.getHeight() * scaleX);
        } else if (scaleX <= scaleY) {
            newW = (int) (bmp.getWidth() * scaleY);
            newH = (int) (bmp.getHeight() * scaleY);
        }
        return Bitmap.createScaledBitmap(bmp, newW, newH, true);
    }

    public static String createDownloadFilePath(String url) {
        //   /data/wallpapers/
        String fileDownloadPath = Tool.getDefaultPath() + Tool.getUrlType(url);
        File dir = new File(fileDownloadPath);
        boolean isCreateFileSuc = true;
        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                isCreateFileSuc = false;
            }
        }

        File file = new File(fileDownloadPath + "/" + getUrlName(url));
        boolean isCreateDirSuc = true;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                isCreateDirSuc = false;
            }
        }
        return isCreateDirSuc && isCreateFileSuc ? file.getAbsolutePath() : "";
    }

    public static String getUrlType(String url) {
        if (isEmpty(url)) {
            return "";
        } else {
            String tmp = getUrlName(url);
            return tmp.substring(tmp.indexOf(".") + 1);
        }
    }

    public static boolean isEmpty(String urls) {
        return urls == null || urls.length() == 0;
    }

    public static String getUrlName(String url) {
        if (isEmpty(url)) {
            return "";
        } else {
            String tmp = url.substring(url.lastIndexOf("/") + 1);
            if (tmp.contains("?")) {
                return tmp.substring(0, tmp.indexOf("?"));
            } else {
                return tmp;
            }
        }
    }

    public static String getDefaultPath() {
        String path = Environment.getExternalStorageDirectory().getPath() + APPConstant.APP_NAME;
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public static boolean isEmpty(Integer integer) {
        return integer == null;
    }


}
