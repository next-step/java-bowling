package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalPitchesTest {
    @DisplayName("투구를 하고 남은 핀의 개수를 반환한다.")
    @ParameterizedTest
    @CsvSource({"3, 7, 7, 0", "10, 0, 0, 10", "2, 8, 3, 5"})
    void bowl(int firstPin, int afterFirst, int nextPin, int afterNext) {
        Pitches finalPitches = new FinalPitches();

        assertAll(
                () -> {
                    int actualAfterFirst = finalPitches.throwBall(firstPin);
                    assertThat(actualAfterFirst).isEqualTo(afterFirst);
                },

                () -> {
                    int actualAfterNext = finalPitches.throwBall(nextPin);
                    assertThat(actualAfterNext).isEqualTo(afterNext);
                }
        );
    }

    @DisplayName("마지막 프레임은 첫번째 투구가 Strike 면 투구 기회가 있다.")
    @Test
    void firstStrikeHasRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(10);

        assertThat(finalPitches.hasChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare 면 투구 기회가 있다.")
    @Test
    void spareHasRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(8);
        finalPitches.throwBall(2);

        assertThat(finalPitches.hasChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare가 아니면 투구 기회가 없다")
    @Test
    void notSpareHasNotRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(8);
        finalPitches.throwBall(1);

        assertThat(finalPitches.hasChance()).isFalse();
    }
}
