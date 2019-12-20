package cn.itcast.controller;

import cn.itcast.elasticClient.ElasticClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class elasticAction {
    @Autowired
    private ElasticClient elasticClient;

    @GetMapping("/delete.json")
    public boolean delete(String relativeUrl,String body){
        boolean isSuccess = elasticClient.requestPost(relativeUrl, body);
        return isSuccess;
    }
}

