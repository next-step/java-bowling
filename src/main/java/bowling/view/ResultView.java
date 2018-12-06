package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Records;
import bowling.domain.score.BowlingScores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static List<String> basicForm
            = Arrays.asList("NAME", " 01 ", " 02 ", " 03 ", " 04 ", " 05 ", " 06 ", " 07 ", " 08 ", " 09 ", " 10 ");
    private static List<String> resultForm
            = Arrays.asList("", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ");
    private static List<String> scoreForm
            = Arrays.asList("   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ");


    public static void drawBasicForm(Player player) {
        System.out.println("|" + basicForm.stream()
                .collect(Collectors.joining("|")));

        resultForm.set(0, player.getName());

        System.out.println("|" + resultForm.stream()
                .collect(Collectors.joining(" |")));
    }

    public static void record(Records records, int frameNum) {
        resultForm.set(frameNum, records.toString());

        System.out.println("|" + basicForm.stream()
                .collect(Collectors.joining("|")));

        System.out.println("|" + resultForm.stream()
                .collect(Collectors.joining(" |")));
    }

    public static void score(BowlingScores scores) {
        IntStream.range(0, scores.getFrameScore().size())
                .forEach(i ->
                        scoreForm.set(i, String.valueOf(scores.getFrameScore().get(i))));

        System.out.println("|    |" + scoreForm.stream()
                .collect(Collectors.joining("  |")));
    }
}
