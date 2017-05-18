package com.laidu.bishe.utils.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * Created by huangpin on 16/11/1.
 */
@Slf4j
public class HttpClientUtil {

    private final static int TIMEOUT = 3000;

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT)
            .setConnectTimeout(TIMEOUT)
            .setConnectionRequestTimeout(TIMEOUT)
            .build();

    private static HttpClientUtil instance = null;

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        return instance;
    }

    public String postFormRequest(String reqURL, String param) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(reqURL);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.info("POST 请求出现异常！" + e);
            e.printStackTrace();
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String postJsonStringRequest(String reqURL, String params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(reqURL);
        try {

            StringEntity entity = new StringEntity(params, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConfig);
            log.info("url = {}, params = {}", reqURL, params);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entityRsponse = response.getEntity();
            if (null != entityRsponse) {
                responseContent = EntityUtils.toString(entityRsponse, "UTF-8");
                log.info("responseContent = {}", responseContent);
                EntityUtils.consume(entityRsponse);
            }
        } catch (Exception e) {
            log.error("[" + reqURL + "]An exception occurred in the service when handling the control request", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public String postXmlStringRequest(String reqURL, String xml) {
        String responseContent = null;
        CloseableHttpClient httpClient = new HttpClientGenerator().generateClient();

        HttpPost httpPost = new HttpPost(reqURL);
        try {
            StringEntity entity = new StringEntity(xml, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("text/xml");
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConfig);
            log.info("REQUEST URL:{}", reqURL);
            log.info("REQUEST XML:{}", xml);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entityRsponse = response.getEntity();
            if (null != entityRsponse) {
                responseContent = EntityUtils.toString(entityRsponse, "UTF-8");
                EntityUtils.consume(entityRsponse);
            }
            log.info("REQUEST BODY:{}", responseContent);
        } catch (Exception e) {
            log.error("[" + reqURL + "]An exception occurred in the service when handling the control request", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public String postFileRequest(String reqURL, String key, InputStream inputStream) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(reqURL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);

        // This attaches the file to the POST:
        builder.addBinaryBody(
                key,
                inputStream,
                ContentType.MULTIPART_FORM_DATA,
                key
        );

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();
        String result = null;
        if (null != responseEntity) {
            result = EntityUtils.toString(responseEntity, "UTF-8");
            EntityUtils.consume(responseEntity);
        }
        return result;
    }

    public static String sendGetRequestToBase64(String reqURL) {
        return Base64.getEncoder().encodeToString(sendGetRequestToBytes(reqURL));
    }

    public static InputStream sendGetRequestToInputStream(String reqURL) {
        return new ByteArrayInputStream(sendGetRequestToBytes(reqURL));
    }

    public static byte[] sendGetRequestToBytes(String reqURL) {
        long responseLength = 0;
        byte[] responseContent = null;
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(reqURL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toByteArray(entity);
                EntityUtils.consume(entity); // Consume response content
            }
            log.info("REQUEST URI: " + httpGet.getURI());
            log.info("RESPONSE STATUS: " + response.getStatusLine());
            log.info("RESPONSE LENGTH: " + responseLength);
            log.info("RESPONSE DETAIL: " + responseContent);
        } catch (ClientProtocolException e) {
            log.error(
                    "A protocol error",
                    e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error("Network anomaly", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public static void main(String[] args) throws IOException {
//        File file = new File("/Users/huangpin/Desktop/iphone.png");
//        String url = "http://o2o-test-lbs-in:3000/asset/resources/upload/image/";
//        JSONObject result = JSON.parseObject(HttpClientUtil.getInstance().postFileRequest(url,"image",new FileInputStream(file)));
//        log.info(result.toJSONString());
//        String downloadUrl = String.format("http://o2o-test-lbs-in:3000/asset/resources/download/image?key=%s",result.getJSONObject("data").getString("image"));
//        System.out.println(HttpClientUtil.getInstance().sendGetRequestToBase64(downloadUrl));
    }
}
