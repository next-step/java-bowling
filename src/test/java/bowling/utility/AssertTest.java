package bowling.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("검증 도구")
class AssertTest {

    @Test
    @DisplayName("객체화 불가능")
    void instance_thrownAssertionError() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ReflectionUtils.newInstance(Assert.class));
    }

    @Test
    @DisplayName("null 검증")
    void notNull() {
        //given
        String message = "message";
        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> Assert.notNull(null, message))
                .withMessage(message);
    }

    @Test
    @DisplayName("빈 컬렉션 검증")
    void notEmpty() {
        //given
        String message = "message";
        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> Assert.notEmpty(Collections.emptyList(), message))
                .withMessage(message);
    }

    @ParameterizedTest
    @DisplayName("텍스트 존재 검증")
    @NullAndEmptySource
    void hasText(String text) {
        //given
        String message = "message";
        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> Assert.hasText(text, message))
                .withMessage(message);
    }
}
