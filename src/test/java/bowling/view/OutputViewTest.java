package bowling.view;

import bowling.domain.Frames;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    @Test
    void printScoreBoard() {
        Frames frames = new Frames();
        frames.play(10);
        frames.play(9);
        frames.play(1);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(9);
        frames.play(1);
        frames.play(10);

        OutputView.printScoreBoard("PES", frames);
    }
}
