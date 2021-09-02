package bowling.domain;

import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BowlingGame {
    private static final int BOWLING_ROUND = 10;
    private static final int MAX_BOWLING_SCORE = 10;
    private static final Random rd = new Random();
    private final String name;

    public BowlingGame(String name) {
        this.name = name;
    }

    public void run() {
        List<String> bowlingGameResult = new ArrayList<>();
        for (int currentRound = 1; currentRound <= BOWLING_ROUND; currentRound++) {

            int currentBowlingScore = rd.nextInt(MAX_BOWLING_SCORE) + 1;
            bowlingGameResult.add(Integer.toString(currentBowlingScore));

            ResultView.printCurrentRound(currentRound, currentBowlingScore);
            ResultView.printResultColumn();
            ResultView.printPlayerName(name);
            for (String score: bowlingGameResult) {
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
