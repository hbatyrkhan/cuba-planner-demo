package com.company.planner2.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service(APIService.NAME)
public class APIServiceBean implements APIService {
    @Override
    public Integer sum(Integer x, Integer y) {
        return Integer.sum(x, y);
    }

    @Override
    public HashMap<String, String> hello(String name) {
        HashMap<String, String> data = new HashMap<>();
        data.put("data","Hello World!!!");
        data.put("comments", name + " krasava!");
        return data;
    }
    @Override
    public HashMap<String, String> hello() {
        HashMap<String, String> data = new HashMap<>();
        data.put("data","Hello World!!!");
        data.put("comments", "You are krasava!");
        return data;
    }
}