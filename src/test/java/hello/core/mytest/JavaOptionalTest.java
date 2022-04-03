package hello.core.mytest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class JavaOptionalTest {

    @Test
    void basic() {
        Optional<String> stringOptional = Optional.ofNullable("value1");

        assertThat(stringOptional.orElse("none")).isEqualTo("value1");
    }

    @Test
    void gitCommitTest() {
        System.out.println("배포 1-3");
    }

    @Test
    void gitTest_Release() {
        System.out.println("개발 배포 3-1");
    }

    @Test
    void gitTest_SearchUIUpgrade() {
        System.out.println("검색_UI_개편 2차_6");
    }
}
