package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        /**
         * 3가지 다 같은 인스턴스이다.
         * - AppConfig.class 를 자바 문법으로만 접근한다면, MemberRepository가 여러번 생성되는 것처럼 보인다.
         * - 하지만 해당 빈 등록 메소드(of AppConfig.class)에 로그를 찍어보면 한번만 출력된다.
         *
         * @Configuration 에 의해 싱글톤을 보장받는다..! (CGLIB)
         * - 스프링은 클래스의 빈을 등록할 때, 해당 클래스를 상속받는 임의의 클래스를 생성한다.
         * - 임의의 클래스는 CGLIB 바이트조작 라이브러리로 만들게 되며, getClass()로 출력 시 XXXCGLIB 으로 확인할 수 있다.
         * - 이 상속받은 클래스 내부에는 빈 등록 여부를 체크하는 로직이 구현되어, 싱글톤을 보장하게 된다.
         *
         * @Configuration 을 제거하고 @Bean 만으로 테스트를 하면, 싱글톤이 깨진다.
         * - CGLIB을 사용하지 않기 때문에 빈 등록 여부 관리가 안된다.
         * - getClass() 출력 시 원래 값이 확인되며, MemberRepository 도 여러번 호훌된다.
         */
        System.out.println("memberService --> memberRepository1 = " + memberRepository1);
        System.out.println("orderService --> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
    }
    
    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    } 
}
