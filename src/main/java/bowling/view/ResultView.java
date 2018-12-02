package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static List<String> basicForm
            = Arrays.asList("NAME", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
    private static List<String> resultForm
            = Arrays.asList("", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
    private static String str = "|";

    public static void drawBasicForm(Player player) {
        str += basicForm.stream()
                .collect(Collectors.joining(" |"));
        System.out.println(str);

        resultForm.set(0, player.getName());

        String result = "| " + resultForm.stream()
                .collect(Collectors.joining(" |"));
        System.out.println(result);
    }

    public static void record(Records records, int frameNum) {
        resultForm.set(frameNum, records.toString());
        String result = "|";
        result += resultForm.stream()
                .collect(Collectors.joining(" |"));
        System.out.println(str);
        System.out.println(result);
    }

}
