package bowling.step3;

import bowling.step3.domain.process.BowlingGame;
import bowling.step3.view.InputView;
import bowling.step3.view.OutputView;

public class BowlingGameMain {
    public static void main(String[] args) {
        BowlingGame bowlingGame = BowlingGame.of(InputView.inputName());

        while (!bowlingGame.isGameOver()){
            OutputView.viewFrameBoard(bowlingGame.play(InputView.inputPitch(bowlingGame)));
        }
    }
}
