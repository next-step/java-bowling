package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("투구를 하고 남은 핀의 개수를 반환한다.")
    @ParameterizedTest
    @CsvSource({"3, 7, 7, 0", "10, 0, 0, 10", "2, 8, 3, 5"})
    void bowl(int firstPin, int afterFirst, int nextPin, int afterNext) {
        FinalFrame finalFrame = new FinalFrame(10);

        assertAll(
                () -> {
                    int actualAfterFirst = finalFrame.bowl(firstPin);
                    assertThat(actualAfterFirst).isEqualTo(afterFirst);
                },

                () -> {
                    int actualAfterNext = finalFrame.bowl(nextPin);
                    assertThat(actualAfterNext).isEqualTo(afterNext);
                }
        );
    }

    @DisplayName("마지막 프레임은 첫번째 투구가 Strike 면 투구 기회가 있다.")
    @Test
    void firstStrikeHasRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(10);

        assertThat(finalFrame.isRemainChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare 면 투구 기회가 있다.")
    @Test
    void spareHasRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(8);
        finalFrame.bowl(2);

        assertThat(finalFrame.isRemainChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare가 아니면 투구 기회가 없다")
    @Test
    void notSpareHasNotRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(8);
        finalFrame.bowl(1);

        assertThat(finalFrame.isRemainChance()).isFalse();
    }
}