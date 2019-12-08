package bowling;

import bowling.domain.Histories;
import bowling.domain.set.FrameSet;
import bowling.domain.set.NormalFrameSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryTest {

    @Test
    void strikeScoreTest() {
        Histories histories = new Histories();

        FrameSet frameSet = NormalFrameSet.createFirst();
        frameSet.play(10);

        histories.add(frameSet);

        frameSet = frameSet.getNext();
        frameSet.play(2);
        frameSet.play(3);

        assertThat(histories.getScores().get(0)).isEqualTo(15);
    }

    @Test
    void spareScoreTest() {
        Histories histories = new Histories();

        FrameSet frameSet = NormalFrameSet.createFirst();
        frameSet.play(3);
        frameSet.play(7);

        histories.add(frameSet);

        frameSet = frameSet.getNext();
        frameSet.play(6);

        assertThat(histories.getScores().get(0)).isEqualTo(16);
    }
}
