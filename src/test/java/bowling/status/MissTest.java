package bowling.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class MissTest {

    @Test
    @DisplayName("두 개의 투구를 진행했을 때 10개 미만의 점수를 얻었을 경우 발생하는 상태")
    void create() {
        Status miss = Miss.of(FIVE_SCORE, FOUR_SCORE);

        assertAll(
                () -> assertThat(miss).isEqualTo(Miss.of(FIVE_SCORE, FOUR_SCORE)),
                () -> assertThat(miss.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("두 개의 투구는 스트라이크 이거나 스페어의 조건을 충족할 수 없습니다")
    void invalidMissCondition() {
        assertAll(
                () -> assertThatThrownBy(() -> Miss.of(STRIKE, STRIKE))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> Miss.of(FIVE_SCORE, FIVE_SCORE))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

}