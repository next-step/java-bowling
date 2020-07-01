package bowling.view;

import bowling.domain.GamePlay;
import bowling.domain.Player;

import java.util.List;

public class OutputView {

    private static final String SEPERATOR = "|";

    public static void output(Player player, GamePlay gamePlay) {
        List<String> result = gamePlay.showFrameResult();
        List<String> scores = gamePlay.showFrameScore();
        List<String> sumScores = gamePlay.showFrameSumScore();
        // ready
        StringBuilder stringBuilderHead = new StringBuilder();
        StringBuilder stringBuilderResult = new StringBuilder();
        StringBuilder stringBuilderScore = new StringBuilder();
        StringBuilder stringBuilderSumScore = new StringBuilder();
        // Title
        stringBuilderHead.append(SEPERATOR + String.format("%5s", "NAME"));
        stringBuilderResult.append(SEPERATOR + String.format("%5s", player));
        stringBuilderScore.append(SEPERATOR + String.format("%5s", "SCORE"));
        stringBuilderSumScore.append(SEPERATOR + String.format("%5s", "TOTAL"));

        for (int i = 0; i < result.size(); i++) {
            stringBuilderHead.append(SEPERATOR);
            stringBuilderHead.append(String.format("%7s", i+1));
            stringBuilderResult.append(SEPERATOR);
            stringBuilderResult.append(String.format("%7s", result.get(i)));
            stringBuilderScore.append(SEPERATOR);
            stringBuilderScore.append(String.format("%7s", scores.get(i)));
            stringBuilderSumScore.append(SEPERATOR);
            stringBuilderSumScore.append(String.format("%7s", sumScores.get(i)));
        }
        stringBuilderHead.append(SEPERATOR);
        stringBuilderResult.append(SEPERATOR);
        stringBuilderScore.append(SEPERATOR);
        stringBuilderSumScore.append(SEPERATOR);
        System.out.println(stringBuilderHead);
        System.out.println(stringBuilderResult);
        System.out.println(stringBuilderScore);
        System.out.println(stringBuilderSumScore);
        System.out.println("");
    }

}
