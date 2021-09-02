package bowling.domain;

import bowling.util.RandomScore;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingGame {
    private static final int BOWLING_ROUND = 10;
    private static final int MAX_BOWLING_SCORE = 10;
    private static final RandomScore randomScore = new RandomScore();

    private final String name;

    public BowlingGame(String name) {
        this.name = name;
    }

    public void run() {
        BowlingUser bowlingUser = new BowlingUser();
        for (int currentRound = 1; currentRound <= BOWLING_ROUND; currentRound++) {
            int currentBowlingScore = randomScore.generate(MAX_BOWLING_SCORE);
            bowlingUser.addResult(currentBowlingScore);

            ResultView.printCurrentRound(currentRound, currentBowlingScore);
            ResultView.printResultColumn();
            ResultView.printPlayerName(name);

            for (String score: bowlingUser.getScores()) {
                ResultView.printScore(score);
            }

            for (int j = currentRound + 1; j <= BOWLING_ROUND; j++) {
                ResultView.printSpace();
            }
            System.out.println();
            System.out.println();

            if (currentBowlingScore < MAX_BOWLING_SCORE) {
                currentBowlingScore = randomScore.generate(MAX_BOWLING_SCORE - currentBowlingScore);
                ResultView.printCurrentRound(currentRound, currentBowlingScore);
                ResultView.printResultColumn();
                ResultView.printPlayerName(name);
                bowlingUser.updateResult(currentBowlingScore);

                for (String score: bowlingUser.getScores()) {
                    ResultView.printScore(score);
                }
                for (int j = currentRound + 1; j <= BOWLING_ROUND; j++) {
                    ResultView.printSpace();
                }
                System.out.println();
                System.out.println();
            }

        }
    }
}
