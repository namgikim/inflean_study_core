package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @ComponentScan
 * - @Component 붙은 클래스를 스캔하여 모두 스프링 빈으로 등록한다.
 * - 빈 이름은 기본적으로 클래스 명에 앞글자를 소문자로 하여 등록한다. (빈 이름을 수동으로 설정할 수도 있다.)
 */
@Configuration
@ComponentScan(
        // 스캔 범위를 지정할 수 있다.
        // 지정하지 않으면, 모든 자바파일과 라이브러리까지 탐색하기 때문에 설정하는 것이 좋다.
        // 1)
        basePackages = "hello.core.member",
        // 2)
        basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지를 기준으로 탬색.
        /**
         * 위 두가지 방식으로도 지정하지 않으면?
         * Default: @ComponentScan를 설정한 클래스의 패키지를 기준으로 탐색한다.
         * 그래서 권장 방법은 설정정보 클래스를 프로젝트 최상단에 두어, 별도로 스캔범위 코드를 작성하지 않도록 하면 좋다.
         *  STS : 시작점으로 하는 클래스에 @SpringBootApplication이 설정되어있고, 하위에 @ComponentScan이 설정되어있기에
         *         STS 는 기본적으로 프로젝트 최상단에서부터 스캔을 한다.
        */

        // 제외할 컴포넌트 유형 설정
        // AppConfig.class 도 스캔 대상이며 하위에 수동으로 등록한 빈 또한 스캔되기 때문에, 안전한 실습/테스트를 위해 해당 코드를 작성함.
        // (이전 실습으로 테스트에 사용한 @Configuration 도 있었다.)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

        /**
         * 빈의 이름을 임의로 설정 후 충돌 테스트
         * Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException ...
         * 위와 같은 에러가 발생된다.
         */
//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }
}
