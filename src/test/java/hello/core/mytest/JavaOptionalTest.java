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
        System.out.println("commit 1");
    }
}
