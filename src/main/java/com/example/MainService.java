package com.example;

import java.util.Map;

public interface MainService {
    boolean doHandle(String name, Long id, Map headers, Map body);
}
