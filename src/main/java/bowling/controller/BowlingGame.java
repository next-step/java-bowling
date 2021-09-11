package bowling.controller;

import bowling.model.NormalRound;
import bowling.model.Round;
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
    private static final int DEFAULT_BONUS_COUNT = 1;
    private static final int FIRST_TRY = 1;
    private static final int SECOND_TRY = 2;

    private static List<List<String>> result = new ArrayList<>();
    private static Round round;
    private static int bonusCount;


    public static void main(String[] args) {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        BowlingGame game = new BowlingGame();
        round = new NormalRound();
        bonusCount = DEFAULT_BONUS_COUNT;

        for (int i = 1; i <= DEFAULT_ROUND_COUNT; i++) {
            List<String> pointList = new ArrayList<>();
            result.add(pointList);
            game.playRound(user, i);
        }

    }

    private void playRound(User user, int numberOfRound) {
        List<String> pointList = getLastList(result);
        boolean isBonusRound = false;
        int totalPoint = 0;
        int tryCount = 0;
        BowlingResult beforeResult = BowlingResult.EMPTY;
        
        while (tryCount++ < SECOND_TRY) {
            String pinCount = ask(numberOfRound + "프레임 투구 : ");
            int score = changeToInt(pinCount);

            totalPoint = calcTotalPoint(isBonusRound, totalPoint, score);

            BowlingResult currentResult = round.play(totalPoint, tryCount, beforeResult);
            beforeResult = currentResult;
            boolean isSkip = round.isSkipNextRound(tryCount, currentResult, isBonusRound);
            tryCount = changeToMaxCount(tryCount, isSkip);

            isBonusRound = round.isBonus(isBonusRound, currentResult);
            tryCount = giveBonusRound(isBonusRound, tryCount);

            pointList.add(changeScore(score, currentResult));
            printBowlingScore(user.getName(), result);

            round = round.next(currentResult, numberOfRound, tryCount);
        }

    }

    private List<String> getLastList(List<List<String>> result) {
        return result.get(result.size()-1);
    }

    private int calcTotalPoint(boolean isBonusRound, int totalPoint, int score) {
        if (isBonusRound) {
            return score;
        }

        totalPoint += score;
        isValidRange(totalPoint);

        return totalPoint;
    }

    private int changeToMaxCount(int tryCount, boolean isSkip) {
        if (isSkip) {
            tryCount = SECOND_TRY;
        }

        return tryCount;
    }
    

    private int giveBonusRound(boolean isBonusRound, int tryCount) {
        if (isBonusRound && bonusCount != 0) {
            tryCount = isSecondTry(tryCount);
            bonusCount -= 1;
        }

        return tryCount;
    }

    private int isSecondTry(int tryCount) {
        if (tryCount == SECOND_TRY) {
            return FIRST_TRY;
        }

        return 0;
    }

}
