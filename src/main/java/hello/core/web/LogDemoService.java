package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // 방식 2. Request Scope 를 Proxy 로 설정하여 빋기
    private final MyLogger myLogger;

    // 방식 1. Provider 로 DL 받기
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        // 방식 1. Provider 로 DL 받기
//        MyLogger myLogger = myLoggerProvider.getObject(); // 필요한 시점에 받기!

        myLogger.log("service id = " + id);
    }
}
