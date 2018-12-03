package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static List<String> basicForm
            = Arrays.asList("NAME", " 01 ", " 02 ", " 03 ", " 04 ", " 05 ", " 06 ", " 07 ", " 08 ", " 09 ", " 10 ");
    private static List<String> resultForm
            = Arrays.asList("", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ");

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
}
