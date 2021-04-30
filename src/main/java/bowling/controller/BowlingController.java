package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        int peopleNumber = inputView.peopleNumber();
        Users users = new Users(inputView.name(peopleNumber));
        resultView.print(users);

        for (int frameNumber = FIRST_ROUND; frameNumber <= LAST_ROUND; frameNumber++) {
            playRound(frameNumber, users);
        }
    }

    private void playRound(int frameNumber, Users users) {
        while (users.hasRemainTurn(frameNumber)) {
            User currentTurnUser = users.currentTurnUser(frameNumber);
            users.proceed(frameNumber, currentTurnUser, new PinNumber(inputView.pinNumber(currentTurnUser.name())));
            resultView.print(users);
        }
    }
}
