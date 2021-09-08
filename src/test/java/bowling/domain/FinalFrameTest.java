package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {

    @DisplayName("1,2투구 스트라이크시 3투구 진행이 가능하다.")
    @Test
    void threeRound() {
        // Given
        Frame givenFinalFrame = FinalFrame.create();

        // When
        while (givenFinalFrame.hasNextFrame()) {
            givenFinalFrame = givenFinalFrame.roll(10);
        }

        // Then
        assertThat(givenFinalFrame.round()).isEqualTo(3);
    }

    @DisplayName("1,2투구 내에 스페어시 3투구 진행이 가능하다.")
    @Test
    void spare() {
        // Given
        Frame givenOneRound = FinalFrame.create();

        // When
        Frame twoRound = givenOneRound.roll(8);
        Frame threeRound = twoRound.roll(2);
        threeRound.roll(10);

        // Then
        assertThat(threeRound.round()).isEqualTo(2);
    }

    @DisplayName("1,2투구 내에 스페어 처리를 못 했는데 3투구 진행시 IllegalArgumentException.")
    @Test
    void throwIllegalArgumentWhenRoll() {
        // Given
        Frame frame = FinalFrame.create();

        // When
        Frame twoRound = frame.roll(2);
        Frame threeRound = twoRound.roll(1);

        // Then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> threeRound.roll(2))
            .withMessage("1,2투구내에 핀을 다 쳐리 못해서 3라운드를 진행 할 수 없습니다.");
    }
}