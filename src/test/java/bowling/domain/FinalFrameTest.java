package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {

    @DisplayName("final Frame에서 1구에 strike이면 3구까지 던질 수 있다.")
    @Test
    void firstRoundStrikeBonusRound() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame frame = FinalFrame.create();
        frames.add(frame);
        frame = frame.roll(10);

        while (frame.hasNextRound()) {
            frames.add(frame);
            frame = frame.roll(2);
        }
        // When & Then

        assertThat(frames.size()).isEqualTo(3);
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(10);
    }

    @DisplayName("final Frame에서 1구,2구에 strike이면 3구까지 던질 수 있다.")
    @Test
    void strikeBonusRound() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame frame = FinalFrame.create();

        while (frame.hasNextRound()) {
            frames.add(frame);
            frame = frame.roll(10);
        }
        // When & Then

        assertThat(frames.size()).isEqualTo(3);
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(10);
    }

    @DisplayName("final Frame에서 1구나 2구에 spare처리하면 3구까지 던질 수 있다.")
    @Test
    void spareWithBonusRound() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame givenFirstRound = FinalFrame.create();
        frames.add(givenFirstRound);

        // When
        Frame secondRound = givenFirstRound.roll(8);
        frames.add(secondRound);

        Frame finalFrame = secondRound.roll(2);
        finalFrame.roll(10);
        frames.add(finalFrame);

        // Then
        assertThat(givenFirstRound.numberOfDownedPins()).isEqualTo(8);
        assertThat(secondRound.numberOfDownedPins()).isEqualTo(2);
        assertThat(finalFrame.numberOfDownedPins()).isEqualTo(10);

        assertThat(frames.size()).isEqualTo(3);
    }

    @DisplayName("final Frame에서 1구나 2구만에 spare 처리를 못하면 3구까지 던질 수 없다.")
    @Test
    void notSpareRoll() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame frame = FinalFrame.create();
        frames.add(frame);

        // When
        Frame second = frame.roll(3);
        frames.add(second);
        Frame bonusFrame = second.roll(5);

        // Then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> bonusFrame.roll(3))
            .withMessage("스페어처리를 못하여서 3라운드를 진행 할 수 없습니다.");
    }
}