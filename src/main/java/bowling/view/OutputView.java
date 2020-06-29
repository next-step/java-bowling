package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEFAULT_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT = "|  %s |      |      |      |      |      |      |      |      |      |      |";

    public static void printDefault(String playerName) {
        printDefaultScoreboard();
        System.out.println(String.format(DEFAULT, playerName));
    }

    private static void printDefaultScoreboard() {
        System.out.println(DEFAULT_SCORE_BOARD);
    }

    public static void printResult(BowlingGame bowlingGame) {
        printDefaultScoreboard();
        printScoreboard(bowlingGame);
    }

    private static void printScoreboard(BowlingGame bowlingGame) {
        System.out.println("|  " + bowlingGame.getPlayer().getName() + " |" + printScore(bowlingGame.getFrames()));
        System.out.println("|      |" + printCalculateScore(bowlingGame.getFrames()));
    }

    private static String printScore(Frames frames) {
        List<String> scores = getScores(frames);
        int resultSize = scores.size();

        String scoreString = scores.stream()
                .map(score -> {
                    if (score.contains("|")) {
                        return " " + score + "  |";
                    }
                    return "  " + score + "   |";
                })
                .collect(Collectors.joining());
        return appendSpaces(scoreString, resultSize);
    }

    private static String appendSpaces(String scoreString, int resultSize) {
        StringBuilder builder = new StringBuilder();

        builder.append(scoreString);

        for (int i = 0; i < 10 - resultSize; i++) {
            builder.append("      |");
        }

        return builder.toString();
    }

    private static List<String> getScores(Frames frames) {
        return frames.getFrames()
                .stream()
                .map(frame -> frame.getStates()
                        .getStates()
                        .stream()
                        .map(State::getValue)
                        .collect(Collectors.joining("|"))
                )
                .collect(Collectors.toList());
    }

    private static String printCalculateScore(Frames frames) {
        List<Integer> calculateScores = frames.getScores();
        int resultSize = calculateScores.size();

        String scoreString = calculateScores.stream()
                .map(score -> "  " + score + "  |")
                .collect(Collectors.joining());
        return appendSpaces(scoreString, resultSize);
    }
}
