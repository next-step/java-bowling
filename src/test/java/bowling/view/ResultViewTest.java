package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultViewTest {

    @Test
    @DisplayName("")
    void head() {
        Player KYH = new Player("kyh");
        Frames frames = new Frames();
        ResultView.printScoreFrame(KYH, frames);
    }

    @Test
    @DisplayName("")
    void test() {
        Player KYH = new Player("kyh");
        Frames frames = new Frames();
        frames.addHittingPinsAtCurrentFrame(3);
        frames.addHittingPinsAtCurrentFrame(7);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(3);
        frames.addHittingPinsAtCurrentFrame(4);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(6);
        frames.addHittingPinsAtCurrentFrame(4);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(0);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(7);
        frames.addHittingPinsAtCurrentFrame(0);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(7);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.addHittingPinsAtCurrentFrame(1);
        frames.addHittingPinsAtCurrentFrame(9);

        ResultView.printEmptyFrame(KYH);
        ResultView.printScoreFrame(KYH, frames);

    }

}