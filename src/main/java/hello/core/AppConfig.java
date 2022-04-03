package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * '나는야 공연 기획자!' - 공연할 배우는 내가 선택한다.
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
 *  구현 객체를 생성하고 연결(생성자 주입)하는 책임을 가지는 별도의 설정 클래스를 만든다.
 *
 *  사용 영역과 구성 영역으로 나눌 수 있게 되었다.
 *
 *  AppConfig는 IoC 컨테이너(혹은 DI 컨테이너)의 역할을 수행하게 된다.
 *      어셈블(조립), 오브젝트 팩토리 라고도 불린다.
 *      주로 DI 컨테이너 라는 용어를 사용한다.
어*/

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
