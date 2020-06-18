package bowling.domain;

import bowling.domain.exceptions.TooManyThrowResultsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ThrowResultsTests {
    @DisplayName("ThrowResult 컬렉션 전달 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<ThrowResult> throwResultList
                = Arrays.asList(new ThrowResult(5), new ThrowResult(5));

        assertThat(new ThrowResults(throwResultList)).isEqualTo(new ThrowResults(throwResultList));
    }

    @DisplayName("최대 2개까지만 관리할 수 있다.")
    @Test
    void createValidationTest() {
        List<ThrowResult> invalidList = Arrays.asList(
                new ThrowResult(2), new ThrowResult(2), new ThrowResult(1));

        assertThatThrownBy(() -> new ThrowResults(invalidList))
                .isInstanceOf(TooManyThrowResultsException.class);
    }
}
