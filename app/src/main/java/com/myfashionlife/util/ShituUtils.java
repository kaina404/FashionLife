package com.myfashionlife.util;

import android.util.Log;

import com.myfashionlife.widget.viewpagerflex.Tool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author: lovexujh
 * @time: 2017/10/28
 * @descripition: 百度识图工具类
 */

public class ShituUtils {

    private static final int TIME_OUT = 10 * 1000;
    private static final String CHARSET = "utf-8";

    public static String postFile(String filePath) {
        long time = System.currentTimeMillis();
        int res;
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";
        String START = "type=commontext&image=";
        File file = new File(filePath);
        try {

            URL url = new URL("https://cloud.baidu.com/aidemo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setChunkedStreamingMode(1024 * 1024);// 1024K
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Length", String.valueOf(file.length()));
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Origin", "https://cloud.baidu.com");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 6.0.1; zh-cn; ZTE A2015 Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/37.0.0.0 MQQBrowser/6.0 Mobile Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Referer", "https://cloud.baidu.com/product/ocr/general");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,en-US;q=0.8");
            conn.setRequestProperty("Cookie", "BAIDUID=B9F1720CB37FEA176A14BC70E2C35CB2:FG=1; BIDUPSID=B9F1720CB37FEA176A14BC70E2C35CB2; BDORZ=AE84CDB3A529C0F8A2B9DCDD1D18B695; H_WISE_SIDS=121246_114747_121279_104886_120150_121261_119383_118886_118877_118852_118831_118792_107311_121254_121533_121215_117336_121862_117432_121666_120594_121562_121421_120943_121041_121362_120482_120550_121614_120852_120996_120035_120262_116408_121360_121134_110085_121716; PSINO=5; BAIDULOC=13521391.59427_3634959.3442833_30_289_1516089393698; H5LOC=1; Hm_lvt_28a17f66627d87f1d046eae152a1c93d=1516089398; Hm_lpvt_28a17f66627d87f1d046eae152a1c93d=1516089398");
            conn.setRequestProperty("contentType", "GBK");

            if (file != null) {
                OutputStream outputSteam = conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);
                StringBuffer sb = new StringBuffer();
                String imgBase64 = Tool.getImgStr(filePath);
                String startCharGBK = URLEncoder.encode("data:image/png;base64,", "GBK");
                String imgBase64GBK = URLEncoder.encode(imgBase64, "GBK");
                sb.append(START).append(startCharGBK).append(imgBase64GBK).append("&image_url=");
                dos.write(sb.toString().getBytes());
                dos.flush();

                res = conn.getResponseCode();

                LogUtil.d("ShituUtils", "response code:" + res);
                if (res == 200) {
                    LogUtil.d("\"ShituUtils\"", "request success");
                    InputStream input = conn.getInputStream();

                    BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));

                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = in.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    result = sb1.toString();
                    LogUtil.d("ShituUtils", "result : " + result);
                } else {
                    LogUtil.d("ShituUtils", "request error");
                }
                Log.d("ShituUtils", "解析图片用时->"+(System.currentTimeMillis() - time));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 百度识图，模仿网页上传图片
     * @param filePath
     * @param requestURL
     * @return
     */
    public static String postFile(String filePath, String requestURL) {
        int res=0;
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";
        File file = new File(filePath);
        try {

            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setChunkedStreamingMode(1024 * 1024);// 1024K
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="+ BOUNDARY);
            if (file != null) {
                OutputStream outputSteam=conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);

                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);

                sb.append("Content-Disposition: form-data; name=\"filedata\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();

                res = conn.getResponseCode();

                LogUtil.d("ShituUtils", "response code:" + res);
                if (res == 200) {
                    LogUtil.d("\"ShituUtils\"", "request success");
                    InputStream input = conn.getInputStream();
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = input.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    result = sb1.toString();
                    LogUtil.d("ShituUtils", "result : " + result);
                } else {
                    LogUtil.d("ShituUtils", "request error");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
