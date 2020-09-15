package bowling.controller;

import bowling.domain.BowlingScore;
import bowling.domain.BowlingUser;
import bowling.ui.BowlingInputView;
import bowling.ui.BowlingResultView;

public class BowlingConsoleSimulator implements BowlingSimulator{

    private BowlingUser bowlingUser;

    @Override
    public void readPlayerName() {
        BowlingInputView.printNameInputPhrase();
        this.bowlingUser = BowlingUser.from(BowlingInputView.readOneLineString());
    }

    @Override
    public void initScoreBoard() {
        this.bowlingUser = BowlingUser.withScoreBoard(bowlingUser.getName());
    }

    @Override
    public void printScoreBoardStatus() {
        BowlingResultView.printStatus(bowlingUser.printableScoreStatus());
    }

    @Override
    public void simulate() {
        while(true) {
            BowlingInputView.printScoreInputPhrase(bowlingUser.currentFrameNo());
            BowlingScore bowlingScore = new BowlingScore(BowlingInputView.readInteger());
            boolean isDone = bowlingUser.bowl(bowlingScore);
            BowlingResultView.printStatus(bowlingUser.printableScoreStatus());

            if(isDone) {
                return;
            }
        }
    }
}
