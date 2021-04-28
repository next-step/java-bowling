package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;

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

        Users users = new Users();
        IntStream.range(0, peopleNumber)
                .mapToObj(i -> new User(inputView.name(i + 1)))
                .forEach(users::add);

        resultView.print(users);

        for (int frameNumber = FIRST_ROUND; frameNumber <= LAST_ROUND; frameNumber++) {
            playPerPerson(frameNumber, users);
        }
    }

    private void playPerPerson(int frameNumber, Users users) {
        users.getUsers()
                .forEach(user -> playRound(frameNumber, users, user));
    }

    private void playRound(int frameNumber, Users users, User user) {
        while (user.hasRemainTurn(frameNumber)) {
            user.proceed(frameNumber, new PinNumber(inputView.pinNumber(user.name())));
            resultView.print(users);
        }
    }
}
