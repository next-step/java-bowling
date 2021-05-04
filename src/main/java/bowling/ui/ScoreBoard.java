package bowling.ui;

import bowling.ui.util.StringPadder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ScoreBoard {
    private final Map<Integer, StringJoiner> dataMap;
    private final String name;

    public ScoreBoard(String name) {
        dataMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            dataMap.put(i, new StringJoiner("|"));
        }
        this.name = name;
    }

    private String getData(int key) {
        return dataMap.get(key).toString();
    }

    public void addData(int key, String formattedScores) {
        StringJoiner stringJoiner = dataMap.get(key);

        stringJoiner.add(formattedScores);
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
        stringJoiner.add(StringPadder.addPadding(name));
        for (int i = 1; i <= 10; i++) {
            stringJoiner.add(StringPadder.addPadding(getData(i)));
        }
        System.out.println(stringJoiner);
    }
}
