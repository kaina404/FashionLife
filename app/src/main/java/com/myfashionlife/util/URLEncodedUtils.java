package com.myfashionlife.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lovexujh on 2017/10/12
 */

public class URLEncodedUtils {

    private static final BitSet URLENCODER = new BitSet(256);

    public static String format(List<? extends NameValuePair> parameters, String charset) {
        StringBuilder result = new StringBuilder();
        Iterator var4 = parameters.iterator();

        while (var4.hasNext()) {
            NameValuePair parameter = (NameValuePair) var4.next();
            String encodedName = encodeFormFields(parameter.getName(), charset);
            String encodedValue = encodeFormFields(parameter.getValue(), charset);
            if (result.length() > 0) {
                result.append("&");
            }

            result.append(encodedName);
            if (encodedValue != null) {
                result.append("=");
                result.append(encodedValue);
            }
        }

        return result.toString();
    }

    private static String encodeFormFields(String content, String charset) {
        return content == null ? null : urlencode(content, charset != null ? Charset.forName(charset) : Charset.forName("UTF-8"), URLENCODER, true);
    }

    private static String urlencode(String content, Charset charset, BitSet safechars, boolean blankAsPlus) {
        if (content == null) {
            return null;
        } else {
            StringBuilder buf = new StringBuilder();
            ByteBuffer bb = charset.encode(content);

            while (true) {
                while (bb.hasRemaining()) {
                    int b = bb.get() & 255;
                    if (safechars.get(b)) {
                        buf.append((char) b);
                    } else if (blankAsPlus && b == 32) {
                        buf.append('+');
                    } else {
                        buf.append("%");
                        char hex1 = Character.toUpperCase(Character.forDigit(b >> 4 & 15, 16));
                        char hex2 = Character.toUpperCase(Character.forDigit(b & 15, 16));
                        buf.append(hex1);
                        buf.append(hex2);
                    }
                }

                return buf.toString();
            }
        }
    }


    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 这里使用了HttpClinet的API。只是为了方便
     *
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

    public static String attachHttpGetParams(String url, HashMap<String, String> valueMap) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = valueMap.entrySet().iterator();
        List<BasicNameValuePair> params = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return attachHttpGetParams(url, params);
    }


}
