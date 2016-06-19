package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/author/{name}/books/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> enqueue(@PathVariable String name,
                                        @PathVariable Long id,
                                        @RequestBody Map<String, Object> body) {
        LOGGER.info("author: {}, id: {}", name, id);
        boolean isSuccessful = mainService.doHandle(name, id, getHeadersInfo(), body);
        if (!isSuccessful) {
            LOGGER.error("[fail] author: {}, id: {}", name, id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    private Map<String, String> getHeadersInfo() {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
