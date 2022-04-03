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
        System.out.println("개발 배포 4-5");
    }

    @Test
    void gitTest_SearchUIUpgrade() {
        System.out.println("검색_UI_개편 4차_5");
    }

    @Test
    void gitTest_ProductComment() {
        System.out.println("상품평 개발 2차_2");
    }

    @Test
    void gitTest_LikeUpgrade() {
        System.out.println("좋아요_기능_개편 1차_3");
        System.out.println("좋아요_기능_개편 1차_4");
        System.out.println("좋아요_기능_개편 1차_7");
        System.out.println("좋아요_기능_개편 1차_9");
        System.out.println("좋아요_기능_개편 2차_2");
    }

    @Test
    void gitTest_backspaceUpgrade() {
        System.out.println("뒤로가기_기능_개편 1차_4");
        System.out.println("뒤로가기_기능_개편 2차_1");
    }
}
