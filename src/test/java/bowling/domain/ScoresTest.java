package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoresTest {

    @Test
    @DisplayName("두 번 굴리고 나면 턴 종료")
    void is_finished_when_bowl_twice() {
        //given
        Scores scores = Scores.of(new Score(5));
        //when
        scores.bowl(new Score(5));
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> scores.bowl(new Score(3)));
    }

    @Test
    @DisplayName("스트라이크면 턴 종료")
    void is_finished_when_strike() {
        //given
        Scores scores = Scores.of(new Score(10));
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> scores.bowl(new Score(3)));
    }

    @Test
    @DisplayName("스코어 합이 10 초과면 IllegalArgumentException")
    void sum_over_10() {
        //given
        Scores scores = Scores.of(new Score(6));
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> scores.bowl(new Score(6)));
    }

    @Test
    @DisplayName("스페어")
    void spare() {
        //given
        Scores scores = Scores.of(new Score(7));
        //when
        scores.bowl(new Score(3));
        //then
        assertAll(
                () -> assertThat(scores.isSpare()).isTrue(),
                () -> assertThat(scores.isMiss()).isFalse()
        );
    }

    @Test
    @DisplayName("미스")
    void miss() {
        //given
        Scores scores = Scores.of(new Score(7));
        //when
        scores.bowl(new Score(1));
        //then
        assertAll(
                () -> assertThat(scores.isMiss()).isTrue(),
                () -> assertThat(scores.isSpare()).isFalse()
        );
    }

    @Test
    @DisplayName("합산")
    void sum() {
        //given
        Scores scores = Scores.of(new Score(1));
        //then
        assertThat(scores.getRemainPins()).isEqualTo(9);
    }

}