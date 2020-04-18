package seul.bowling.view;

import org.junit.jupiter.api.Test;
import seul.bowling.domain.Frames;

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
