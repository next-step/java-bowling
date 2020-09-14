package bowling.controller;

import bowling.domain.BowlingUser;
import bowling.domain.ScoreBoard;
import bowling.ui.BowlingInputView;
import bowling.ui.BowlingResultView;

public class BowlingConsoleSimulator implements BowlingSimulator{

    private BowlingUser bowlingUser;
    private ScoreBoard scoreBoard;

    public void readPlayerName() {
        BowlingInputView.printNameInputPhrase();
        this.bowlingUser = BowlingUser.from(BowlingInputView.readOneLineString());
    }

    public void initScoreBoard() {
        this.scoreBoard = ScoreBoard.from(bowlingUser.getName());
    }

    public void printScoreBoardStatus() {
        BowlingResultView.printStatus(scoreBoard.printableStatus());
    }
}
