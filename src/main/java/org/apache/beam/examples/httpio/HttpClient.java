package org.apache.beam.examples.httpio;

import io.netty.handler.codec.http.HttpMethod;
import org.asynchttpclient.*;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

public class HttpClient {
    AsyncHttpClient httpClient;
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);


    public HttpClient() {
        DefaultAsyncHttpClientConfig.Builder configBuilder = config()
                .setConnectTimeout(90000)
                .setRequestTimeout(90000)
                .setMaxRequestRetry(3)
                .setMaxConnectionsPerHost(100)
                .setReadTimeout(90000);

        ThreadPoolTaskExecutor threadFactory = new ThreadPoolTaskExecutor();
        threadFactory.setMaxPoolSize(32);
        threadFactory.setKeepAliveSeconds(300);

        configBuilder.setThreadFactory(threadFactory);

        AsyncHttpClientConfig clientConfig = configBuilder.build();
        httpClient = asyncHttpClient(clientConfig);
    }

    public void send(String input){
        Request request = new RequestBuilder()
                .setUrl("https://reqbin.com/sample/post/json")
                .setMethod(HttpMethod.POST.name())
                //.setHeader("Authorization", "XXXX")
                .setBody(input)
                .build();
        ListenableFuture<Response> responseListenableFuture = httpClient.executeRequest(request, new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                System.out.println("Done");
                return null;
            }
        });
    }
}
