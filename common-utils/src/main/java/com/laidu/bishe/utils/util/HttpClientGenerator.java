package com.laidu.bishe.utils.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by cw on 15-9-11.
 */
@Slf4j
public class HttpClientGenerator {

    /**
     * Logger based on slf4j
     */
    private PoolingHttpClientConnectionManager connectionManager;

    public HttpClientGenerator() {
        this("SSLv3");
    }

    public HttpClientGenerator(final String sslProtocol) {
        try {
            // Trust all cert not only self-signed
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                    // bypass
                }

                public void checkServerTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                    // bypass
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            // Set SSLv3 for CMC connection
            SSLContext sslContext = SSLContext.getInstance(sslProtocol);
            sslContext.init(null, new TrustManager[]{tm}, null);

            // Build ssl context
            //SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();

            // Pain socket factory for http
//            ConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();

            Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
                    .build();
            connectionManager = new PoolingHttpClientConnectionManager(reg);
            connectionManager.setDefaultMaxPerRoute(20);
            connectionManager.setMaxTotal(200);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("初始化证书失败",e);
        }
    }


    public CloseableHttpClient generateClient() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connectionManager);

        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build();
        httpClientBuilder.setDefaultSocketConfig(socketConfig);
        return httpClientBuilder.build();
    }

}
