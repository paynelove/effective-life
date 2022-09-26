package com.liam.effective.life.util;


import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableBiMap;
import com.liam.effective.life.dto.data.ErrorCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.net.ssl.HostnameVerifier;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Slf4j
@Component
public class HttpUtil {

    public static OkHttpClient client;
    public static MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json; charset=utf-8");

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES);
        try {
            //ssl verifier
            KeyStore trustStore;
            trustStore = KeyStore.getInstance(KeyStore
                    .getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactoryImp ssl = new SSLSocketFactoryImp(KeyStore.getInstance(KeyStore.getDefaultType()));

            HostnameVerifier doNotVerify = (hostname, session) -> true;

            builder.sslSocketFactory(ssl.getSSLContext().getSocketFactory(), ssl.getTrustManager())
                    .hostnameVerifier(doNotVerify);
            client = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static String get(String url, Map<String, Object> urlParam, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty(urlParam)) {
            urlParam.forEach((k, v) -> urlBuilder.addQueryParameter(k, v instanceof String ? (String) v : JacksonUtil.toJSON(v)));
        }

        Request request = reqBuilder.url(urlBuilder.build()).build();

        Response response = client.newCall(request).execute();

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static byte[] getInputStream(String url, Map<String, Object> urlParam, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty(urlParam)) {
            urlParam.forEach((k, v) -> urlBuilder.addQueryParameter(k, v instanceof String ? (String) v : JacksonUtil.toJSON(v)));
        }

        Request request = reqBuilder.url(urlBuilder.build()).build();

        Response response = client.newCall(request).execute();

        byte[] bytes = response.body().bytes();
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, response.body());
        }
        response.close();
        return bytes;
    }

    @SneakyThrows
    public static String post(String url, Map<String, Object> urlParam, Object param, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        String json;
        if (param == null) {
            json = "{}";
        } else if (param instanceof String) {
            json = param.toString();
        } else {
            json = JacksonUtil.toJSON(param);
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty(urlParam)) {
            urlParam.forEach((k, v) -> urlBuilder.addQueryParameter(k, v instanceof String ? (String) v : JacksonUtil.toJSON(v)));
        }

        Request request = reqBuilder.url(urlBuilder.build()).post(RequestBody.create(MEDIA_TYPE_TEXT, json)).build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return JacksonUtil.toJSON(ImmutableBiMap.of("code", 404, "message", "404"));
        }

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static String patch(String url, Map<String, Object> urlParam, Object param, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        String json;
        if (param == null) {
            json = "{}";
        } else if (param instanceof String) {
            json = param.toString();
        } else {
            json = JSON.toJSONString(param);
        }

        Request request = reqBuilder.url(url).patch(RequestBody.create(MEDIA_TYPE_TEXT, json)).build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return JacksonUtil.toJSON(ImmutableBiMap.of("code", 404, "message", "404"));
        }

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static String put(String url, Map<String, Object> urlParam, Object param, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        String json;
        if (param == null) {
            json = "{}";
        } else if (param instanceof String) {
            json = param.toString();
        } else {
            json = JSON.toJSONString(param);
        }

        Request request = reqBuilder.url(url).put(RequestBody.create(MEDIA_TYPE_TEXT, json)).build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return JacksonUtil.toJSON(ImmutableBiMap.of("code", 404, "message", "404"));
        }

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static String delete(String url, Map<String, Object> urlParam, Object param, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        String json;
        if (param == null) {
            json = "{}";
        } else if (param instanceof String) {
            json = param.toString();
        } else {
            json = JSON.toJSONString(param);
        }

        Request request = reqBuilder.url(url).delete(RequestBody.create(MEDIA_TYPE_TEXT, json)).build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return JacksonUtil.toJSON(ImmutableBiMap.of("code", 404, "message", "404"));
        }

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static String post(String url, Map<String, Object> urlParam, String fileUrl, byte[] bytes, Map<String, String> headers) {

        URI uri = new URI(fileUrl);
        String fileName = uri.getPath();
        String contentType = MediaTypeFactory.getMediaType(fileName).orElse(org.springframework.http.MediaType.IMAGE_JPEG).toString();
        headers.put("Content-Type", contentType);

        Request.Builder reqBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(reqBuilder::addHeader);
        }

        Request request = reqBuilder.url(url).post(RequestBody.create(MediaType.parse(contentType), bytes)).build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return JacksonUtil.toJSON(ImmutableBiMap.of("code", 404, "message", "404"));
        }

        String responseString = IOUtils.toString(response.body().byteStream(), String.valueOf(StandardCharsets.UTF_8));
        if (!response.isSuccessful()) {
            log.error("request[url={}, header={}] error={}", url, headers, responseString);
        }
        response.close();
        return responseString;
    }

    @SneakyThrows
    public static String post(String url, Object param, Map<String, String> headers) {
        return post(url, null, param, headers);
    }


    public static String patch(String url, Object param, Map<String, String> headers) {
        return patch(url, null, param, headers);
    }

    public static String put(String url, Object param, Map<String, String> headers) {
        return put(url, null, param, headers);
    }

    public static String delete(String url, Object param, Map<String, String> headers) {
        return delete(url, null, param, headers);
    }

    public static <T extends Serializable> T postParseObject(HttpServletRequest httpServletRequest, Class<T> clazz) {
        if (httpServletRequest.getContentType().equals(APPLICATION_JSON.toString())) {
            return servletParseJson(httpServletRequest, clazz);
        } else if (httpServletRequest.getContentType().equals(MULTIPART_FORM_DATA.toString())) {
            return servletFormDataParseJson(httpServletRequest, clazz);
        }
        throw new BizException("解析参数错误");
    }

    public static <T extends Serializable> T servletParseJson(HttpServletRequest httpServletRequest, Class<T> clazz) {
        try {
            BufferedReader streamReader =
                    new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            log.debug("servletParseJson string:{}", responseStrBuilder);
            return JacksonUtil.json2Object(clazz, responseStrBuilder.toString());
        } catch (Exception e) {
            throw new BizException(ErrorCode.ARGS_ERROR.getErrCode(), "Json转换异常[" + e.getMessage() + "]");
        }
    }

    public static <T extends Serializable> T servletFormDataParseJson(HttpServletRequest httpServletRequest, Class<T> clazz) {
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        MultipartHttpServletRequest multipartRequest =
                WebUtils.getNativeRequest(httpServletRequest, MultipartHttpServletRequest.class);
        if (multipartRequest != null) {
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            for (Map.Entry<String, MultipartFile> m : multipartFileMap.entrySet()) {
                postParameters.add(m.getKey(), m.getValue().getResource());
            }
        }
        String respString = JacksonUtil.toJSON(postParameters);
        log.debug("servletParseJson string:{}", respString);
        return JacksonUtil.json2Object(clazz, respString);
    }

}
