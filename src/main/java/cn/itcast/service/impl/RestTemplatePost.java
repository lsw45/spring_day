package cn.itcast.service.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplatePost {

    public static void main(String[] args){

    }

    public String doPost(String mid,String templateId,Integer channelId,byte[] codeFile){
        String result = "";

        ByteArrayResource resource = new ByteArrayResource(codeFile){
            @Override
            public String getFilename() {
                 return "code";
            }
        };

        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("templateId", templateId);
        params.add("codeFile",  resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("mid", mid);
        headers.set("channelId", channelId.toString());

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();

        // Http的操作需要有异常处理，无论是HttpClient还是封装的restTemplate当请求超时或者返回Http状态码为4xx或者5xx时，均会抛出异常
        try{
            ResponseEntity<String> responseEntity = restTemplate.exchange("www.baidu.com", HttpMethod.POST, requestEntity, String.class);
            result = responseEntity.getBody();
            System.out.println("券系统返回："+responseEntity);
            System.out.println("请求体："+requestEntity);
        }catch (Exception e){

        }

        return result;
    }
}
