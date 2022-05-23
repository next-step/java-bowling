package bowling.domain.score;

import bowling.domain.Pins;
import bowling.exception.InvalidMissPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @Test
    @DisplayName("스트라이크 생성을 확인한다")
    void strike() {
        //given, when
        Score strike = Score.strike();

        //then
        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike.score()).isEqualTo(10),
                () -> assertThat(strike.finishCalculation()).isFalse()
        );
    }

    @Test
    @DisplayName("스페어 생성을 확인한다")
    void spare() {
        //given, when
        Score spare = Score.spare();

        //then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare.score()).isEqualTo(10),
                () -> assertThat(spare.finishCalculation()).isFalse()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("올바른 핀이 입력될 경우 미스 점수 생성을 확인한다")
    void miss(int pinCount) {
        //given, when
        Score miss = Score.miss(Pins.create(pinCount));

        //then
        assertAll(
                () -> assertThat(miss).isInstanceOf(Score.class),
                () -> assertThat(miss).isNotNull(),
                () -> assertThat(miss.finishCalculation()).isTrue()
        );

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    @DisplayName("미스를 생성하기 위한 핀이 유효하지 않으면 예외를 반환한다")
    void invalidMiss(int pinCount) {
        assertThatThrownBy(() -> Score.miss(Pins.create(pinCount))).isInstanceOf(InvalidMissPinsException.class);
    }

    @Test
    @DisplayName("추가 점수를 연산한다")
    void addBonusScore() {
        //given
        Score score = Score.strike();

        //when
        Score addedScore = score.addBonusScore(5);

        //then
        assertThat(addedScore.score()).isEqualTo(15);
    }

    @Test
    @DisplayName("보너스 연산이 끝났음을 확인한다")
    void finishCalculation() {
        //given
        Score spareScore = Score.spare();

        //when
        Score addedScore = spareScore.addBonusScore(3);

        //then
        assertThat(addedScore.finishCalculation()).isTrue();
    }

    @Test
    @DisplayName("연산이 불가능한 상태임을 반환한다")
    void isUnavailable() {
        //given, when
        Score unavailableScore = Score.unavailable();

        //then
        assertThat(unavailableScore.isUnavailable()).isTrue();
    }

    @Test
    @DisplayName("스코더의 반환하는 문자열을 확인한다")
    void testToString() {
        assertAll(
                () -> assertThat(Score.unavailable().toString()).isBlank(),
                () -> assertThat(Score.strike().toString()).isEqualTo("10")
        );

    }
}