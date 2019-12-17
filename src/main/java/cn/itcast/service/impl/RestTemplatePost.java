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
        ResponseEntity<String> responseEntity = restTemplate.exchange("/upload",
                HttpMethod.POST, requestEntity, String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();

        result = responseEntity.getBody();
        System.out.println("券系统返回："+responseEntity);
        System.out.println("请求体："+requestEntity);
        return result;
    }
}
