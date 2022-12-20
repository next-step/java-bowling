package bowling.view;

import bowling.model.Player;
import bowling.model.frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printBowlResult(Player player, Frames frames) {
        printTitle();
        printResult(player.getName(), frames);

    }

    private static void printTitle() {
        List<String> titles = new ArrayList<>();
        titles.add("NAME");

        for (int i = 1; i <= 10; i++) {
            titles.add(String.format(" %02d ", i));
        }

        printFormat(titles);
    }

    private static void printResult(String name, Frames frames) {
        List<String> result = new ArrayList<>();
        result.add(String.format("%4s", name));
        int size = frames.size();
        for (int i = 0; i < size; i++) {
            result.add(frames.getCurrentFrame().getState().toString());
        }
        for (int i = size; i < 10; i++) {
            result.add(String.format("%4s", " "));
        }

        printFormat(result);
    }

    private static void printFormat(List<String> values) {
        System.out.println(values.stream()
                .collect(Collectors.joining(" | ", "| ", " |")));
    }
}
