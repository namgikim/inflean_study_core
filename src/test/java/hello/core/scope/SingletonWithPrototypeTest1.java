package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean PrototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean1.addCount();
        assertThat(PrototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean PrototypeBean2 = ac.getBean(PrototypeBean.class);
        PrototypeBean2.addCount();
        assertThat(PrototypeBean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class); // 같은 빈
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class); // 같은 빈
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton") // 생략가능 (기본이 싱글톤)
    @RequiredArgsConstructor
    static class ClientBean {
//        private final PrototypeBean prototypeBean;  // 생성시점에 주입되며, 한번 주입된 객체가 해당 싱글톤 빈에 종속된다.
                                                    // => 이 의도가 아니다. 매번 새로운 빈이여야 한다.
                                                    // (싱글톤 빈에 접근할 때마다 새로운 prototype 빈을 받는 방식은 다음시간에 설명.)
                                                    // - 가장 간단한 방법은 Dependency Lookup (DL)으로 직접 찾아 사용하기.(비추천)
                                                    // - !! 해결방법: Provider.

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        /**
         * Provider 선언
         * - 스프링에서 빈을 생성해준다.
         * - ObjectFactory 는 기본 기능만 제공. 편의 기능 사용하려면 ObjectProvider 사용하기.
         * - 기본 제공 기능이기에 권장 방법.
         */
//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        /**
         * JSR-330 Provider 선언
         * - 자바 표준이다.
         * - 'javax:inject:javax.inject:1' 라이브러리를 사용한다.
         * - 메소드는 getObject() 기능을 하는 get()을 사용한다.
         * - get() 하나를 지원하는 매우 단순한 라이브러리이다.
         * - 스프링 외의 컨테이너에서 사용할 일이 있을 때 사용하자.
         */
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            /**
             * Provider 사용
             * - ApplicationContext 를 주입받아 'DL' 로 직접 찾아오는 일련의 과정을 대신 해주는 기능을 한다.
             * - 그래서 호출 될 때마다 새로운 객체를 불러온다.
             * - 이 외에도 많은 상황에서 사용되는 기능이다.
             */
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // Spring Provider 적용.

            /**
             * JSR-330 Provider 사용
             * - 마찬가지로 DL 이 진행된다.
             * - 이 외에도 많은 상황에서 사용되는 기능이다.
             */
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // JSR-330 Provider 적용.

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
