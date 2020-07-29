package com.company.planner2.service;

import java.util.HashMap;

public interface APIService {
    String NAME = "planner2_APIService";
    public Integer sum(Integer x, Integer y);
    public HashMap<String, String> hello(String name);
    public HashMap<String, String> hello();
}