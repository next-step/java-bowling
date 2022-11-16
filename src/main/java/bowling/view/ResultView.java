package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.view.dto.BowlRecord;
import bowling.view.dto.BowlingGameFrameRecord;
import bowling.view.dto.ScoreDto;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int END_FRAME = 10;

    public void printScoreBoard(String name, List<BowlingGameFrameRecord> frameRecords) {
        printScoreBoardTitle();

        printBowlRecords(name, frameRecords);
        printScores(frameRecords);

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

    private void printBowlRecords(String name, List<BowlingGameFrameRecord> frameRecords) {
        List<String> frameContents = frameRecords.stream()
            .map(BowlingGameFrameRecord::getBowlRecords)
            .map(this::convertBowlRecordsToDescription)
            .collect(toList());

        printScoreBoardContent(name, frameContents);
    }

    private void printScores(List<BowlingGameFrameRecord> frameRecords) {
        List<ScoreDto> scores = frameRecords.stream()
            .map(BowlingGameFrameRecord::getScore)
            .collect(toList());

        printScoreBoardContent("", convertScoresToDescription(scores));
    }

    private void printScoreBoardContent(String nameContent, List<String> frameContents) {
        StringJoiner joiner = new StringJoiner("|");
        joiner.add("");
        joiner.add(String.format(" %4s ", nameContent));
        for (int i = 0; i < END_FRAME; ++i) {
            joiner.add(String.format("    %-5s ", frameContents.get(i)));
        }
        joiner.add("");
        System.out.println(joiner);
    }

    private String convertBowlRecordsToDescription(List<BowlRecord> bowlRecords) {
        return bowlRecords.stream()
            .map(this::convertSingleBowlRecordToDescription)
            .collect(Collectors.joining("|"));
    }

    private String convertSingleBowlRecordToDescription(BowlRecord bowlRecord) {
        if (bowlRecord.isStrike()) {
            return "X";
        }

        List<String> pinsList = bowlRecord.getValues().stream()
            .map(value -> Integer.toString(value))
            .map(this::convertGutter)
            .collect(toList());

        if (bowlRecord.isSpare()) {
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

    private List<String> convertScoresToDescription(List<ScoreDto> scores) {
        List<String> descriptions = new ArrayList<>();
        int sumOfScore = 0;

        for (ScoreDto score : scores) {
            String description = score.getScoreDescription(sumOfScore);
            sumOfScore += score.getValue();

            descriptions.add(description);
        }

        return descriptions;
    }
}
