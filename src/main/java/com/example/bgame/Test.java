package com.example.bgame;

import com.example.bgame.model.external.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        Root object = restTemplate.getForObject("https://api.boardgameatlas.com/api/search?order_by=rank&ascending=false&pretty=true&client_id=HxiKNy5EnG", Root.class);

        System.out.println(object.getGames());
        System.out.println(object.getGames().get(20).getFaq());
        System.out.println(object.getGames().get(20).getId());
    }

}
