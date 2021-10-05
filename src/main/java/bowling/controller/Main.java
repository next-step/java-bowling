package bowling.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static bowling.view.InputView.ask;
import static bowling.view.InputView.askDigit;
import static bowling.view.OutputView.changeScore;
import static bowling.view.OutputView.printResult;

import bowling.CannotBowlException;
import bowling.model.BowlingGame;
import bowling.model.NormalRound;
import bowling.model.State;
import bowling.model.User;

public class Main {
    public static List<Integer> scoreResult = new ArrayList<>();
    public static LinkedList<String> stateResult = new LinkedList<>();
    public static void main(String[] args) throws CannotBowlException {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        BowlingGame game = new BowlingGame(new NormalRound());

        while (!game.isEndGame()) {
            int pinCount = askDigit(game.getFrameNo() + "프레임 투구 : ");

            State state = game.bowl(pinCount);
            List<Integer> scores = game.getScore();

            stateResult.add(changeScore(state));
            scoreResult.addAll(scores);

            printResult(userName);
        }
    }
}
