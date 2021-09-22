package bowling.domain.frame;

import bowling.domain.score.Score;
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

        Frame round1 = FinalFrame.create();
        round1.roll(10);
        frames.add(round1);

        Frame round2 = round1.nextRound().orElseThrow(IllegalArgumentException::new);
        round2.roll(2);
        frames.add(round2);

        Frame nextRound3 = round2.nextRound().orElseThrow(IllegalArgumentException::new);
        nextRound3.roll(10);
        frames.add(nextRound3);

        // When & Then
        assertThat(frames.size()).isEqualTo(3);
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(Score.from(10));
    }

    @DisplayName("final Frame에서 1구,2구에 strike이면 3구까지 던질 수 있다.")
    @Test
    void strikeBonusRound() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame frame = FinalFrame.create();
        frames.add(frame);

        while (frame.hasNextRound()) {
            frame.roll(10);
            frame = frame.nextRound().orElse(frame);
            frames.add(frame);
        }

        // When & Then
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(Score.from(10));
        assertThat(frames.get(0).frameInfo().round()).isEqualTo(0);
        assertThat(frames.get(2).numberOfDownedPins()).isEqualTo(Score.from(10));
        assertThat(frames.get(2).frameInfo().round()).isEqualTo(2);
    }

    @DisplayName("final Frame에서 2구에 spare처리하면 3구까지 던질 수 있다.")
    @Test
    void spareWithBonusRound() {
        // Given
        List<Frame> frames = new ArrayList<>();
        Frame givenFirstRound = FinalFrame.create();
        frames.add(givenFirstRound);

        // When
        givenFirstRound.roll(8);

        Frame secondRound = givenFirstRound.nextRound().orElseThrow(IllegalArgumentException::new);
        frames.add(secondRound);
        secondRound.roll(2);

        Frame finalFrame = secondRound.nextRound().orElseThrow(IllegalArgumentException::new);
        finalFrame.roll(10);
        frames.add(finalFrame);

        // Then
        assertThat(givenFirstRound.numberOfDownedPins()).isEqualTo(Score.from(8));
        assertThat(secondRound.numberOfDownedPins()).isEqualTo(Score.from(2));
        assertThat(finalFrame.numberOfDownedPins()).isEqualTo(Score.from(10));

        assertThat(frames.size()).isEqualTo(3);
    }

    @DisplayName("final Frame에서 1구나 2구만에 spare 처리를 못하면 3구까지 던질 수 없다.")
    @Test
    void notSpareRoll() {
        // Given
        Frame frame = FinalFrame.create();

        // When
        frame.roll(3);
        Frame second = frame.nextRound().orElseThrow(IllegalArgumentException::new);
        second.roll(5);

        // Then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> second.nextRound().orElseThrow(() -> new IllegalArgumentException("스페어처리를 못하여서 3라운드를 진행 할 수 없습니다.")))
            .withMessage("스페어처리를 못하여서 3라운드를 진행 할 수 없습니다.");

    }
}