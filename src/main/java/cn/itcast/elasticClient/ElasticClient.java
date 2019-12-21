package cn.itcast.elasticClient;

import com.sun.istack.internal.NotNull;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Base64;

@Service
@PropertySource("classpath:application_test.yml")
public class ElasticClient {
    private static final Logger log = LoggerFactory.getLogger(ElasticClient.class);

    @Value("#{elasticsearch.url}")
    private String baseUrl;

    private String authorization;
    private RestTemplate restTemplate;

    @Autowired
    public void ElasticClient(@Value("#{elasticsearch.username}") String name,
                              @Value("#{elasticsearch.password}") String password){
        this.restTemplate = initRestTemplate();
        authorization = Base64.getEncoder().encodeToString(String.format("%s:%s", name, password).getBytes());
    }

    private RestTemplate initRestTemplate(){
        HttpComponentsClientHttpRequestFactory httpClientFactory = new HttpComponentsClientHttpRequestFactory();
        httpClientFactory.setConnectTimeout(2000);
        httpClientFactory.setReadTimeout(10000);
        HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        httpClientFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(httpClientFactory);
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(@NotNull ClientHttpResponse clientHttpResponse) throws IOException {
                return true;
            }
            @Override
            public void handleError(@NotNull ClientHttpResponse clientHttpResponse) throws IOException {
            }
        };
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

    public ResponseEntity<String> requestGet(String relativeUrl, String body) {
        return request(relativeUrl, HttpMethod.POST, body);
    }

    public boolean requestPost(String relativeUrl, String body) {
        ResponseEntity<String> responseEntity = request(relativeUrl, HttpMethod.POST, body);
        if(responseEntity == null || HttpStatus.REQUEST_TIMEOUT.equals(responseEntity.getStatusCode())){
            return false;
        }
        return true;
    }

    public ResponseEntity<String> request(String relativeUrl, HttpMethod method, String body) {
        if(!relativeUrl.startsWith("/")){
            relativeUrl = "/" + relativeUrl;
        }
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "Basic " + authorization);
        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl + relativeUrl, method, entity, String.class);
            if(HttpStatus.CONFLICT.equals(response.getStatusCode())){
                log.debug("版本号冲突, relativeUrl: {}, method: {}, body: {}", relativeUrl, method, body);
            } else if(HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
                log.info("文档不存在, relativeUrl: {}, method: {}, body: {}", relativeUrl, method, body);
            } else if(!HttpStatus.OK.equals(response.getStatusCode()) && !HttpStatus.CREATED.equals(response.getStatusCode())){
                log.info("es请求非200, relativeUrl: {}, method: {}, body: {}, response: {}", relativeUrl, method, body, response);
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
            log.warn("elasticsearch请求异常, relativeUrl: {}, method: {}, body: {}", relativeUrl, method, body);
        }
        return response;
    }
}
