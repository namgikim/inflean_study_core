package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {

        //ConfigurableApplicationContext : close()를 위해 상위 메소드 사용.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * 빈 콜백 호출 방법 2
         * 빈을 등록할 때, 초기화 메소드와 종료 메소드를 지정할 수 있다.
         * 특별한 기능: destroyMethod 를 명시하지 않아도 추론하여 호출해준다.
         * - 기본값이 '(inferred)' 이며 close, shutdown 이라는 메소드를 자동으로 호출한다.
         * - 외부 라이브러리를 @Bean 으로 등록하여 사용할 때 종료해주어야 하는 일이 종종 있다.
         *    이때, 대부분 종료 메소드의 이름이 close, shutdown 으로 생성되어 있다.
         */
//        @Bean(initMethod = "init"/*, destroyMethod = "close"*/)
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
