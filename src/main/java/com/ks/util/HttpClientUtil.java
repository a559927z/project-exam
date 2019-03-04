package com.ks.util;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2019年03月04日 15:10
 * @Verdion 1.0 版本
 * ${tags}
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.chinahrd.utils.CollectionKit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    private static String CONTENT_TYPE = "Content-Type";
    private static String JSON_UTF8 = "application/json;charset=utf-8";
    private static String ACCEPT = "Accept";
    private static String UTF8 = "utf-8";

    public HttpClientUtil() {
    }

    public static String get(String url) {
        return get((Map)null, url, UTF8);
    }

    public static String get(Map<String, Object> paramester, String url) {
        return get(paramester, url, UTF8);
    }

    private static String get(Map<String, Object> paramester, String url, String encode) {
        String queryString = "";
        String bodyString;
        if (!CollectionKit.isEmpty(paramester)) {
            Set<String> keySet = paramester.keySet();

            for(Iterator var5 = keySet.iterator(); var5.hasNext(); queryString = queryString + paramester.get(bodyString)) {
                bodyString = (String)var5.next();
                queryString = queryString + "&";
                queryString = queryString + bodyString;
                queryString = queryString + "=";
            }
        }

        if (queryString.startsWith("&")) {
            queryString = queryString.substring(1, queryString.length());
        }

        url = url + "?" + queryString;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        bodyString = null;

        try {
            HttpResponse response = client.execute(httpGet);
            bodyString = EntityUtils.toString(response.getEntity());
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }

        return bodyString;
    }

    public static String postBody(Map<String, String> parameter, String url) {
        return post(parameter, url, UTF8);
    }

    private static String post(Map<String, String> parameter, String url, String encode) {
        HttpClient httpclient = HttpClientBuilder.create().build();
        List<NameValuePair> params = new ArrayList();
        if (parameter != null && !parameter.isEmpty()) {
            Set<String> keySet = parameter.keySet();
            Iterator var6 = keySet.iterator();

            while(var6.hasNext()) {
                String key = (String)var6.next();
                NameValuePair pair = new BasicNameValuePair(key, (String)parameter.get(key));
                params.add(pair);
            }
        }

        HttpPost httppost = new HttpPost(url);
        httppost.setHeader(CONTENT_TYPE, JSON_UTF8);
        String jsonStr = null;

        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, encode));
            HttpResponse response = httpclient.execute(httppost);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            jsonStr = EntityUtils.toString(entity, encode);
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            httppost.releaseConnection();
        }

        return jsonStr;
    }

    public static String postBody(String json, String url) {
        return postBody(json, url, (Map)null);
    }

    private static String postBody(String json, String url, Map<String, String> header) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader(CONTENT_TYPE, JSON_UTF8);
        httppost.addHeader(ACCEPT, JSON_UTF8);
        if (header != null && !header.isEmpty()) {
            Set<String> keys = header.keySet();
            if (keys != null) {
                Iterator var6 = keys.iterator();

                while(var6.hasNext()) {
                    String key = (String)var6.next();
                    httppost.addHeader(key, (String)header.get(key));
                }
            }
        }

        String jsonStr = null;

        try {
            httppost.setEntity(new StringEntity(json, UTF8));
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            jsonStr = EntityUtils.toString(entity, UTF8);
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            httppost.releaseConnection();
        }

        return jsonStr;
    }
}
