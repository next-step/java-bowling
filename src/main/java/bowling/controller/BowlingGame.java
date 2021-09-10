package bowling.controller;

import bowling.model.NormalRound;
import bowling.model.User;
import bowling.model.BowlingResult;

import java.util.ArrayList;
import java.util.List;

import static bowling.model.BowlingValidator.changeToInt;
import static bowling.model.Point.isValidRange;
import static bowling.view.InputView.ask;
import static bowling.view.OutputView.changeScore;
import static bowling.view.OutputView.printBowlingScore;

public class BowlingGame {
    public static final int DEFAULT_ROUND_COUNT = 10;
    private static final int MAX_TRY_COUNT = 2;

    private static List<List<String>> result = new ArrayList<>();
    private static bowling.model.Round round;


    public static void main(String[] args) {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        round = new NormalRound();
        for (int i = 1; i <= DEFAULT_ROUND_COUNT; i++) {
            List<String> pointList = new ArrayList<>();
            result.add(pointList);
            playRound(user, i);
        }

    }

    private static void playRound(User user, int numberOfRound) {
        List<String> pointList = getLastList(result);
        boolean isBonusRound = false;
        int totalPoint = 0;
        int tryCount = 0;
        int maxTryCount = MAX_TRY_COUNT;

        while (tryCount++ < maxTryCount) {
            String pinCount = ask(numberOfRound + "프레임 투구 : ");
            int score = changeToInt(pinCount);

            totalPoint = calcTotalPoint(isBonusRound, totalPoint, score);

            BowlingResult roundResult = round.play(totalPoint, tryCount);
            boolean isSkip = round.isSkipNextRound(tryCount, roundResult, isBonusRound);
            tryCount = changeToMaxCount(tryCount, isSkip);

            isBonusRound = round.isBonus(isBonusRound, roundResult);
            maxTryCount = giveBonusRound(isBonusRound, maxTryCount);

            pointList.add(changeScore(score, roundResult));
            printBowlingScore(user.getName(), result);

            round = round.next(roundResult, numberOfRound, tryCount);
        }

    }

    private static List<String> getLastList(List<List<String>> result) {
        return result.get(result.size()-1);
    }

    private static int changeToMaxCount(int tryCount, boolean isSkip) {
        if (isSkip) {
            tryCount = MAX_TRY_COUNT;
        }

        return tryCount;
    }

    private static int calcTotalPoint(boolean isBonusRound, int totalPoint, int score) {
        if (isBonusRound) {
            return score;
        }

        totalPoint += score;
        isValidRange(totalPoint);

        return totalPoint;
    }

    private static int giveBonusRound(boolean isBonusRound, int maxTryCount) {
        if (isBonusRound) {
            maxTryCount += 1;
        }

        return maxTryCount;
    }
}
