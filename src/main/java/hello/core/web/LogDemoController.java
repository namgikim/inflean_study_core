package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    // 방식 2. Request Scope 를 Proxy 로 설정하여 빋기
    private final MyLogger myLogger;

    // 방식 1. Provider 로 DL 받기
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    /**
     * 보통 요청이 오면 뷰 템플릿을 거쳐 렌더링해서 나가게 된다.
     * 지금 실습은 화면이 없기 때문에, 문자열 그대로 표현되도록 @RequestBody 로 구현한다.
     *
     * MyLogger 를 요청할 때마다 가져오기 때문에, 각 요청끼리 MyLogger 를 혼용해서 사용할 수 없게 된다.
     * - Request Scope 빈은 하나의 요청이 오고 나갈때에 유효하다.
     * - 웹에서만 사용할 수 있다.
     * - **Prototype Scope 와 달리 생명주기가 끝날때까지 관리해주기 때문에, @PreDestroy 와 같은 종료 메서드를 사용할 수 있다.
     *
     * 또한, 같은 HTTP 요청 안에서는 myLoggerProvider.getObject() 를 호출하는 곳이 다르더라도 같은 스프링 빈이 반환된다.
     * - 예) LogDemoController.logDemo() / logDemoService.logic()
     *
     * MyLogger 주입받는 방법
     * 방법1. Provider 롤 DL 받아 추출해서 사용하기
     * 방법2. Request Scope 를 Proxy 로 설정하기
     */
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        // 방식 1. Provider 로 DL 받기
//        MyLogger myLogger = myLoggerProvider.getObject(); // 필요한 시점에 받기!

        /**
         * 방법 2.
         * Proxy 설정에 의해 CGLIB 로 가짜가 들어있다.
         * 가짜를 주입해놓고, myLogger 를 사용할 때 진짜를 찾아사용하게 되며 마치 DL 을 하는 것과 같은 효과를 가진다.
         * 이 또한 지연시키는 일종으로, Provider 와 비슷한 메커니즘이다.
         */
        System.out.println("myLogger = " + myLogger.getClass());

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
