package bowling.ui;

import bowling.ui.util.StringPadder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ScoreBoard {
    private final Map<Integer, String> dataMap;

    public ScoreBoard(String name) {
        dataMap = new HashMap<>();
        dataMap.put(0, name);
    }

    private String getData(int key) {
        return dataMap.getOrDefault(key, "");
    }

    public String addData(int key, String value) {
        return dataMap.put(key, value);
    }

    public void printScoreBoard() {
        printTopInfoBar();
        printBottomInfoBar();
    }

    private void printTopInfoBar() {
        System.out.print("| NAME |");
        for(int i = 1; i <= 9; i++) {
            System.out.printf("  %02d  |", i);
        }
        System.out.printf("  10  |%n");
    }

    private void printBottomInfoBar() {
        StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
        for (int i = 0; i <= 10; i++) {
            stringJoiner.add(StringPadder.addPadding(getData(i)));
        }
        System.out.println(stringJoiner);
    }
}
