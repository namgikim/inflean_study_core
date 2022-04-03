package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /**
     * 빈 콜백 호출 방법 1-1
     * InitializingBean interface
     * 의존관계 주입이 완료된 후에 동작하는 콜백 메소드
     * (초창기 기술로, 최근에는 사용되지 않음)
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * 빈 콜백 호출 방법 1-2
     * DisposableBean interface
     * 빈이 종료될 때 호출되는 콜백 메소드
     * (초창기 기술로, 최근에는 사용되지 않음)
     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    /**
     * @Bean(initMethod = "init", destroyMethod = "close") 에 의해서
     *  빈 둥록 시 호출된다.
     */
//    public void init() {
//        System.out.println("NetworkClient.init");
//        connect();
//        call("초기화 연결 메시지");
//    }
    /**
     * @Bean(initMethod = "init", destroyMethod = "close") 에 의해서
     *  빈 종료 시 호출된다.
    */
//    public void close() {
//        System.out.println("NetworkClient.close");
//        disconnect();
//    }

    /**
     * 빈 콜백 호출 방법 3-1
     * - 최신 스프링의 권장 방식
     * - javax 패키지 기능이기 때문에 스프링 외에서도 사용 가능. (자바 표준)
     * - 단점, 외부 라이브러리에 적용 못함. (이때는 방법 2를 사용한다.)
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    /**
     * 빈 콜백 호출 방법 3-2
     */
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
