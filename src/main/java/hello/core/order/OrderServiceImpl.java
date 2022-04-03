package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //auto
//@RequiredArgsConstructor // Lombok
public class OrderServiceImpl implements OrderService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // --> DiscountPolicy 라는 추상에만 의존해야 하는데, FixDisCountPolicy 라는 구체 클래스에 의존하고 있다. DIP 위반.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // --> 구현부를 변경할 때, 소스코드가 변경되면 안된다. OCP 위반.

    // 아래 생성자 주입 대신에, 필드 주입이 가능하다. (단, 매우 권장하지 않음. 테스트 코딩과 디버깅에 취약함.)
    // 테스트 코드에는 사용해도 된다.
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    /**
     * 생성자 주입 방식
     * 가장 보편적으로 사용하는 방식이다.
     * - final 키워드를 사용하여 완성도를 높일 수 있다. (필히 초기화를 해야하기에 오류 방지)
     * - 테스트 코드르 자바로만 작성할 경우, 이 외의 방식으로 주입하고 있다면 누락될 수도 있다. (주입해야 한다는 점을 한번에 인지하기 어려움)
     * -
     */
    @Autowired //auto (생성자가 1개만 선언되어있다면, 생략가능)
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // --> Lombok > @RequiredArgsConstructor 에 의해 성성됨.
    // --> 생성자, final 둘다 잡으면서 코드 수를 줄일 수 있다.

    /**
     * 롬복 라이브러리
     * - 생성자 주입 방식도 번잡하다고 생각된다면? 사용하기
     * - spring.io 에서 프로젝트 생성 시 추가할 수 있음.(그만큼 인기있는 라이브러리)
     * - build.gradle 에서 라이브러리 추가.
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
