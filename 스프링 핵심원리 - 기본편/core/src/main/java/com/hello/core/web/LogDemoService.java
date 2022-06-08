package com.hello.core.web;

import com.hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        // myLogger는 이 때 생성됨
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id" + id);
    }
}
