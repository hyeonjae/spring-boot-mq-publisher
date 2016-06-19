package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("mainService")
public class MainServiceImpl implements MainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceImpl.class);

    @Autowired
    MyQueue myQueue;

    @Override
    public boolean doHandle(String name, Long id, Map headers, Map body) {
        Map msg = createMessage(name, id, headers, body);
        return myQueue.enqueue(msg);
    }

    private Map createMessage(String name, Long id, Map headers, Map body) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", name);
        msg.put("id", id);
        msg.put("requestHeader", headers);
        msg.put("requestBody", body);
        return msg;
    }
}
