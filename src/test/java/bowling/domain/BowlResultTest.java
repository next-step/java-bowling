package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class BowlResultTest {

    @Test
    @DisplayName("처음 투구 결과 생성 테스트")
    void of() {
        assertThatCode(() -> BowlResult.of(10)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("투구 값이 1 ~10 범위 밖인 경우 오류를 발생시킨다.")
    void of_fail(int downPin) {
        assertThatThrownBy(() -> BowlResult.of(downPin))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("다음 투구 결과 생성 테스트")
    void next() {
        assertThatCode(() -> BowlResult.of(2).next(8)).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("점수판에 표출 할 결과값을 반환한다")
    void getResult() {
        assertAll(() -> {
            assertThat(BowlResult.of(10).getResult()).isEqualTo("X");
            assertThat(BowlResult.of(1).getResult()).isEqualTo("1");
            assertThat(BowlResult.of(8).next(2).getResult()).isEqualTo("8|/");
            assertThat(BowlResult.of(6).next(3).getResult()).isEqualTo("6|3");
            assertThat(BowlResult.of(5).next(0).getResult()).isEqualTo("5|-");
            assertThat(BowlResult.of(0).next(0).getResult()).isEqualTo("-|-");
        });
    }
}
