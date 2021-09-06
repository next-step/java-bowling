package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NormalFrameTest {

    @DisplayName("1투구에 핀 10개를 다 쓰러뜨렸다면 2투구는 없다.")
    @Test
    void rollStrikeAt1stRound() {
        // Given
        Frame frame = NormalFrame.create();

        // When
        frame.roll(10);

        // Then
        assertThat(frame.round()).isEqualTo(0);
        assertThat(frame.pins().numberOfPinDowns()).isEqualTo(10);
    }

    @DisplayName("1투구에 핀 10개를 다 못 쓰러뜨렸다면 2투구를 할 수 있다.")
    @Test
    void rollAt2stRound() {
        // Given
        Frame frame = NormalFrame.create();

        // When
        Frame nextFrame = frame.roll(8);
        nextFrame.roll(1);

        // Then
        assertThat(nextFrame.round()).isEqualTo(1);
        assertThat(nextFrame.pins().numberOfPinDowns()).isEqualTo(9);
    }

    @DisplayName("NormalFrame은 3라운드 진행시 throw IllegalArgument")
    @Test
    void roll3thRound() {
        // Given
        Frame frame = NormalFrame.create();

        // When && Then
        Frame nextFrame = frame.roll(5);
        Frame nextFrame2 = nextFrame.roll(1);

        assertThatIllegalArgumentException().isThrownBy(() -> nextFrame2.roll(1));
    }
}