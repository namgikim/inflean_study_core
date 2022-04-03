package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 최근에는 스프링부트 사용이 많아지면서 XML 기반으로 구현하는 경우는 적다.
 * 레거시 같은 이전 프로젝트에는 아직 남아있다.
 * 빌드 없이 코드변경이 가능하다는 장점이 있지만, 자바코드가 개발하기에 더 용이하다(?)
 */
public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
