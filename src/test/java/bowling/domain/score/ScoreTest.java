package bowling.domain.score;

import bowling.domain.frame.Frames;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    private Pins strikePins = Pins.of().knockOver(new BowlCount(10));
    private Pins FirstBowlPins = Pins.of().knockOver(new BowlCount(5));
    private Pins SparePins = Pins.of().knockOver(new BowlCount(5));

    @DisplayName("Strike 1개 누적 확인")
    @Test
    void oneStrikeAccumulate() {
        Frames frames = Frames.create();

        frames.bowl(strikePins);
        frames.bowl(Pins.GUTTER_PINS);
        frames.bowl(FirstBowlPins);

        final List<Score> scores = frames.getScores();
        assertThat(scores.get(0)).isEqualTo(new Score(15, 0));
    }

    @DisplayName("Strike 2개 누적 확인")
    @Test
    void twoStrikeAccumulate() {
        Frames frames = Frames.create();

        frames.bowl(strikePins);
        frames.bowl(strikePins);
        frames.bowl(FirstBowlPins);

        final List<Score> scores = frames.getScores();
        assertThat(scores.get(0)).isEqualTo(new Score(25, 0));
    }

    @DisplayName("Strike 3개 누적 확인")
    @Test
    void threeStrikeAccumulate() {
        Frames frames = Frames.create();

        frames.bowl(strikePins);
        frames.bowl(strikePins);
        frames.bowl(strikePins);

        final List<Score> scores = frames.getScores();
        assertThat(scores.get(0)).isEqualTo(new Score(30, 0));
    }

    @DisplayName("Spare 1개 누적 확인")
    @Test
    void oneSpareAccumulate() {
        Frames frames = Frames.create();

        frames.bowl(FirstBowlPins);
        frames.bowl(SparePins);
        frames.bowl(strikePins);

        final List<Score> scores = frames.getScores();
        assertThat(scores.get(0)).isEqualTo(new Score(20, 0));
    }

    @DisplayName("Miss 확인")
    @Test
    void miss() {
        Frames frames = Frames.create();

        frames.bowl(FirstBowlPins);
        frames.bowl(Pins.GUTTER_PINS);

        final List<Score> scores = frames.getScores();
        assertThat(scores.get(0)).isEqualTo(new Score(5, 0));
    }
}