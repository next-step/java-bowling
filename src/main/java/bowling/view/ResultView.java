package bowling.view;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int END_FRAME = 10;

    public void printScoreBoard(String name) {
        printScoreBoardTitle();
        // TODO: 볼링 게임과 연관지어서 출력하도록 바꿀 것
        printScoreBoardContent(name, List.of("", "", "", "", "", "", "", "", "", ""));
        System.out.println();
        // printScoreBoardContent(name, List.of("X", "8", "8|", "8|/", "7", "7|", "7|-", "", "", ""));
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printScoreBoardTitle() {
        List<String> frameNumbers = IntStream.range(START_FRAME, END_FRAME + 1)
            .mapToObj(number -> String.format("%02d", number))
            .collect(Collectors.toList());

        printScoreBoardContent("NAME", frameNumbers);
    }

    private void printScoreBoardContent(String nameContent, List<String> frameContents) {
        StringJoiner joiner = new StringJoiner("|");
        joiner.add("");
        joiner.add(String.format(" %4s ", nameContent));
        for (int i = 0; i < END_FRAME; ++i) {
            joiner.add(String.format("  %-3s ", frameContents.get(i)));
        }
        joiner.add("");
        System.out.println(joiner);
    }
}
