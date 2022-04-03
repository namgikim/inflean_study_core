package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 싱글톤 패턴을 구현하는 방법 (중에 하나)
 * - 클래스 객체를 싱글톤으로 생성해보기 (Step1..3)
 * - 오직 하나의 메소드로만 참조하도록 하여 2개 이상
 * (기존 코드에 영향을 주지 않으면서 구현해보기 위해 Test에 임시로 작성하며 실습함.)
 * <p>
 * 단점
 * - privte 키워드에 의해 유연성이 떨어지고, DIP 를 위반하게 되기 때문에, 스프링의 싱글톤 컨테이너를 활용하자.
 */
public class SingletonService {

    // Step.1
    private static final SingletonService instance = new SingletonService(); //(static 으로 선언하여 1개만 존재하도록 테스트 환경 구성.)

    // Step.2
    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * (중요!) 생성자를 private 로 선언하여 외부 클래스에서 인스턴스 생성을 하지 못하도록 한다.
     */
    // Step.3 (끝)
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출!");
    }
}
