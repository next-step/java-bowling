package domain;

import org.junit.Test;
import view.InputView;
import view.OutputView;

import static org.junit.Assert.*;

public class BowlingGameTest {
    @Test
    public void 노스트라이크노스페어() {
        BowlingGame.startGame();
        BowlingGame.getFrames();
        Player player = new Player("TET");
        while (BowlingGame.hasNext()){
            BowlingGame.play(4);
            OutputView.printFrameBoard(Frame.MAX_FRAME);
            OutputView.playerOfGameBoard(player, BowlingGame.getFrames());
        }
    }

    @Test
    public void 노스트라이크올스페어() {
        BowlingGame.startGame();
        BowlingGame.getFrames();
        Player player = new Player("TET");
        while (BowlingGame.hasNext()){
            BowlingGame.play(5);
            OutputView.printFrameBoard(Frame.MAX_FRAME);
            OutputView.playerOfGameBoard(player, BowlingGame.getFrames());
        }
    }

    @Test
    public void 올스트라이크() {
        BowlingGame.startGame();
        BowlingGame.getFrames();
        Player player = new Player("TET");
        while (BowlingGame.hasNext()){
            BowlingGame.play(10);
            OutputView.printFrameBoard(Frame.MAX_FRAME);
            OutputView.playerOfGameBoard(player, BowlingGame.getFrames());
        }
    }

}