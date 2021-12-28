package bowling.domain.state;

import bowling.domain.PinsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpareTest {

    private ThrowingState spare;

    @BeforeEach
    void beforeEach() {
        spare = Spare.create(PinsTest.FIVE, PinsTest.FIVE);
    }

    @DisplayName("Spare가 아닌 두 번의 투구로 Spare를 생성하는 경우")
    @Test
    void createFailed() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Spare.create(PinsTest.FOUR, PinsTest.FIVE));
    }

    @DisplayName("한 프레임에서 Spare 후, 투구하는 경우 예외")
    @Test
    void bowlFailed() {
        // when & then
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> spare.bowl(PinsTest.FIVE));
    }

    @DisplayName("한 프레임에서 Spare 후, 종료된 상태 여부 확인")
    @Test
    void isEnd() {
        // when & then
        assertThat(spare.isEnd()).isTrue();
    }

    @DisplayName("Spare 표기법 확인")
    @Test
    void symbol() {
        // when & then
        assertThat(spare.symbol()).isEqualTo(String.format("%s|/", PinsTest.FIVE.getValue()));
    }

    @DisplayName("현재 Score 반환 확인")
    @Test
    void score() {
        // when & then
        assertThat(spare.score()).isEqualTo(Score.of(10, 1));
    }

    @DisplayName("1 Strike 1 Spare 이후, Score 반환 확인")
    @Test
    void scoreAfter() {
        // given
        Score strike = Score.of(10, 2);
        // when & then
        assertThat(spare.scoreAfter(strike)).isEqualTo(Score.withNonRemainingPitches(20));
    }
}
