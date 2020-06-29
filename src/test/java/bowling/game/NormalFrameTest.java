package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("투구를 하고 남은 의핀 개수를 반환한다.")
    @ParameterizedTest
    @CsvSource({"3, 7, 7, 0", "10, 0, 0, 0", "2, 8, 3, 5"})
    void bowl(int firstPin, int afterFirst, int nextPin, int afterNext) {
        Frame normalFrame = new NormalFrame(1);

        assertAll(
                () -> {
                    int actualAfterFirst = normalFrame.bowl(firstPin);
                    assertThat(actualAfterFirst).isEqualTo(afterFirst);
                },

                () -> {
                    int actualAfterNext = normalFrame.bowl(nextPin);
                    assertThat(actualAfterNext).isEqualTo(afterNext);
                }
        );
    }

    @DisplayName("첫 투구가 Strike가 아니면 그 프레임은 투구 기회가 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void firstBowlIsNotStrikeRemainChance(int firstPinCount) {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(firstPinCount);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(true);
    }

    @DisplayName("첫 투구가 Strike면 그 프레임은 더 이상 투구 기회가 없다.")
    @Test
    void firstBowlIsStrikeNotRemainChance() {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(10);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(false);
    }

    @DisplayName("2개의 투구를 모두 완료하면 그 프레임은 더 이상 투구 기회가 없다.")
    @ParameterizedTest
    @CsvSource({"9, 1", "8, 1", "0, 7", "0, 0"})
    void bowlTwiceNotRemainChance(int firstPinCount, int nextPinCount) {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(firstPinCount);
        normalFrame.bowl(nextPinCount);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(false);
    }

    @DisplayName("나보다 번호가 1 큰 프레임을 만든다.")
    @Test
    void createNextFrame() {
        int firstNumber = 1;
        Frame firstFrame = new NormalFrame(firstNumber);

        Frame nextFrame = firstFrame.createNextFrame();

        assertThat(nextFrame.getFrameNumber().getNumber()).isEqualTo(firstNumber + 1);
    }
}