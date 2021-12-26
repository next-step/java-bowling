package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultViewTest {

    @Test
    @DisplayName("화면 출력 - 빈 프레임 보드 출력")
    void head() {
        Player KYH = new Player("KYH");
        ResultView.printEmptyFrame(KYH);
    }

    @Test
    @DisplayName("화면 출력 - 스코어 프레임 보드 출력")
    void test() {
        Player KYH = new Player("KYH");
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
        ResultView.printScoreFrame(KYH, frames);
    }

}