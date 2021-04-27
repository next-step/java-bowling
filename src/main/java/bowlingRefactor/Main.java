package bowlingRefactor;

import bowlingRefactor.domain.FrameNumber;
import bowlingRefactor.domain.Frames;
import bowlingRefactor.domain.Player;
import bowlingRefactor.view.InputView;
import bowlingRefactor.view.ResultView;

public class Main {
    public static void main(String[] args) {
        String bowlerName = InputView.inputUserNames();
        Player bowler = new Player(bowlerName);

        ResultView.printScoreBoard(bowler);
        for (int i = 0; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            play(bowler, i);
        }
    }

    private static void play(Player bowler, int index) {
        while (bowler.isLeftPin(index)) {
            int pin = InputView.inputPinCount(bowler.get(index).getFrameNumber());
            bowler.hit(index, pin);
            ResultView.printScoreBoard(bowler);
        }
    }
}
