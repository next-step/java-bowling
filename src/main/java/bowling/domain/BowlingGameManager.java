package bowling.domain;

import bowling.util.RandomScore;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameManager {
    private static final int BOWLING_ROUND = 10;
    private static final int MAX_BOWLING_SCORE = 10;
    private static final RandomScore randomScore = new RandomScore();

    private final List<BowlingUser> bowlingUsers = new ArrayList<>();

    public BowlingGameManager(String name) {
        bowlingUsers.add(new BowlingUser(name));
    }

    public void run() {
        for (int currentRound = 1; currentRound <= BOWLING_ROUND; currentRound++) {
            runByRound(currentRound);
        }
    }

    private void runByRound(int currentRound) {
        BowlingUser bowlingUser = bowlingUsers.get(0);

        int firstBowlingScore = randomScore.generate(MAX_BOWLING_SCORE);
        int secondBowlingScore = 0;
        bowlingUser.addResult(firstBowlingScore);
        printRoundResult(currentRound, bowlingUser, firstBowlingScore);

        if (firstBowlingScore < MAX_BOWLING_SCORE) {
            secondBowlingScore = randomScore.generate(MAX_BOWLING_SCORE - firstBowlingScore);
            bowlingUser.updateResult(secondBowlingScore);
            printRoundResult(currentRound, bowlingUser, secondBowlingScore);
        }

        int totalBowlingScore = firstBowlingScore + secondBowlingScore;
        if (currentRound == BOWLING_ROUND && totalBowlingScore < MAX_BOWLING_SCORE) {
            int thirdBowlingScore = randomScore.generate(MAX_BOWLING_SCORE - totalBowlingScore);
            bowlingUser.updateThirdResult(thirdBowlingScore);
            printRoundResult(currentRound, bowlingUser, thirdBowlingScore);
        }
    }

    private void printRoundResult(int currentRound, BowlingUser bowlingUser, int firstBowlingScore) {
        printRoundInfo(currentRound, firstBowlingScore);
        printUserScoreResult(bowlingUser);
        printRemainingRound(currentRound);
    }

    private void printRemainingRound(int currentRound) {
        for (int j = currentRound + 1; j <= BOWLING_ROUND; j++) {
            ResultView.printSpace();
        }
        System.out.println();
        System.out.println();
    }

    private void printUserScoreResult(BowlingUser bowlingUser) {
        ResultView.printPlayerName(bowlingUser.getName());
        for (String score: bowlingUser.getScores()) {
            ResultView.printScore(score);
        }
    }

    private void printRoundInfo(int currentRound, int currentBowlingScore) {
        ResultView.printCurrentRoundInfo(currentRound, currentBowlingScore);
        ResultView.printResultColumn();
    }
}
