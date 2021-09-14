package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.BowlingResult;
import bowling.model.User;

import java.util.ArrayList;
import java.util.List;

import static bowling.view.InputView.ask;
import static bowling.view.InputView.askDigit;
import static bowling.view.OutputView.changeScore;
import static bowling.view.OutputView.printBowlingScore;

public class Main {
    public static final int MAX_ROUND = 10;
    public static final int SECOND_TRY = 2;

    public static List<BowlingResult> bowlingResults = new ArrayList<>();
    public static List<List<String>> allResults = new ArrayList<>();

    public static void main(String[] args) {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        BowlingGame game = new BowlingGame();
        for (int i = 1; i <= MAX_ROUND; i++) {
            List<String> pointList = new ArrayList<>();
            allResults.add(pointList);
            playRound(game, user, i);
        }
    }

    private static void playRound(BowlingGame game, User user, int index) {
        int tryCount = 0;
        int maxRound = SECOND_TRY;
        game.roundInit();
        while (tryCount++ < maxRound) {
            int pinCount = askDigit(index + "프레임 투구 : ");
            maxRound += game.play(pinCount);

            getLastList().add(changeScore(pinCount, getBowlingResult()));
            printBowlingScore(user.getName(), allResults);
        }
    }

    private static BowlingResult getBowlingResult() {
        return bowlingResults.get(bowlingResults.size() - 1);
    }

    private static List<String> getLastList() {
        return allResults.get(allResults.size()-1);
    }
}
