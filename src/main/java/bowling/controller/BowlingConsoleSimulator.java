package bowling.controller;

import bowling.domain.BowlingUser;
import bowling.ui.BowlingInputView;

public class BowlingConsoleSimulator implements BowlingSimulator{

    private BowlingUser bowlingUser;

    public void readPlayerName() {
        BowlingInputView.printNameInputPhrase();
        this.bowlingUser = BowlingUser.from(BowlingInputView.readOneLineString());
    }
}
