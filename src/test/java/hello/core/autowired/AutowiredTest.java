package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // 클래스를 넣으면 빈으로 등록된다.
    }

    static class TestBean {

        @Autowired(required = false) // true 로 할 경우, 필히 주입되어야 하는데 대상이 없으니 오류발생함.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); // 호출 조차 안됨.
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); //호출이 됨. (null)
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); //호출이 됨. (Optional.empty)
        }
    }
}
