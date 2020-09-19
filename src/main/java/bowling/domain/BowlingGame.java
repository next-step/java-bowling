package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public void startGame() {
        InputView inputView = new InputView();
        String name = inputView.getParticipants();
        Participants participants = Participants.of(name);
        ResultView resultView = new ResultView();
        resultView.printResult(participants);
        int currentFrame = 0;
        while (BowlingFrame.TOTAL_FRAME > currentFrame) {
            int hit = inputView.getHit(currentFrame + 1);
            currentFrame = participants.getBowlingFrame().hit(hit);
            resultView.printResult(participants);
        }
    }
}
