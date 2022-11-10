package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.BowlingGameFrameRecord;
import bowling.domain.frame.Score;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int END_FRAME = 10;

    public void printScoreBoard(String name, List<BowlingGameFrameRecord> frameRecords) {
        printScoreBoardTitle();

        List<String> frameContents = frameRecords.stream()
            .map(BowlingGameFrameRecord::getScores)
            .map(this::convertScoresToDescription)
            .collect(toList());

        printScoreBoardContent(name, frameContents);
        System.out.println();
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printScoreBoardTitle() {
        List<String> frameNumbers = IntStream.range(START_FRAME, END_FRAME + 1)
            .mapToObj(number -> String.format("%02d", number))
            .collect(toList());

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

    private String convertScoresToDescription(List<Score> scores) {
        return scores.stream()
            .map(this::convertSingleScoreToDescription)
            .collect(Collectors.joining("|"));
    }

    private String convertSingleScoreToDescription(Score score) {
        if (score.isStrike()) {
            return "X";
        }

        List<String> pinsList = score.getValues().stream()
            .map(value -> Integer.toString(value))
            .map(this::convertGutter)
            .collect(toList());

        if (score.isSpare()) {
            pinsList.set(pinsList.size() - 1, "/");
        }

        return String.join("|", pinsList);
    }

    private String convertGutter(String pins) {
        if (pins.equals("0")) {
            return "-";
        }

        return pins;
    }
}
