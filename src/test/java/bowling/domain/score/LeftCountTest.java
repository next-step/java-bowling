package bowling.domain.score;

import bowling.exception.InvalidLeftCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LeftCountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("leftCount를 생성한다.")
    void createLeftCount(int leftCount) {
        assertThat(LeftCount.from(leftCount)).isEqualTo(LeftCount.from(leftCount));
    }

    @Test
    @DisplayName("일반적인 LeftCount를 생성한다.")
    void create() {
        // given
        // when
        final LeftCount leftCount = LeftCount.create();

        // then
        assertAll(
                () -> assertThat(leftCount).isEqualTo(LeftCount.from(0)),
                () -> assertThat(leftCount.hasLeftCount()).isFalse()
        );

    }

    @Test
    @DisplayName("strike LeftCount를 생성한다.")
    void strikeLeftCount() {
        // given
        // when
        final LeftCount strike = LeftCount.strike();

        // then
        assertAll(
                () -> assertThat(strike).isEqualTo(LeftCount.from(2)),
                () -> assertThat(strike.hasLeftCount()).isTrue()
        );
    }

    @Test
    @DisplayName("spare LeftCount를 생성한다.")
    void spareLeftCount() {
        // given
        // when
        final LeftCount spare = LeftCount.spare();

        // then
        assertAll(
                () -> assertThat(spare).isEqualTo(LeftCount.from(1)),
                () -> assertThat(spare.hasLeftCount()).isTrue()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    @DisplayName("LeftCount는 0~2 사이의 숫자만 허용된다.")
    void validate(int invalidLeftCount) {
        assertThatThrownBy(() -> LeftCount.from(invalidLeftCount))
                .isInstanceOf(InvalidLeftCountException.class)
                .hasMessage(InvalidLeftCountException.INVALID_LEFT_COUNT);
    }

    @Test
    @DisplayName("decrease를 사용하면 1회 줄어든다.")
    void decrease() {
        // given
        final LeftCount leftCount = LeftCount.from(1);

        // when
        final LeftCount decreaseCount = leftCount.decrease();

        // then
        assertThat(decreaseCount).isEqualTo(LeftCount.from(0));
    }
}
