package bowling.domain;

import bowling.util.RandomScore;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            BowlingUser bowlingUser = bowlingUsers.get(0);

            int currentBowlingScore = randomScore.generate(MAX_BOWLING_SCORE);
            bowlingUser.addResult(currentBowlingScore);

            ResultView.printCurrentRound(currentRound, currentBowlingScore);
            ResultView.printResultColumn();
            ResultView.printPlayerName(bowlingUser.getName());

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
                ResultView.printPlayerName(bowlingUser.getName());
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
