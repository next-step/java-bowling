package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.frames.Frame;
import bowling.domain.frames.Frames;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingConsoleOutputView {

    public void print(final Bowling bowling) {
        printHeader();
        printName(bowling.getName());
        printFrames(bowling.getFrames());
    }

    private void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void printName(final Name player) {
        System.out.printf("| %4s |", player.value());
    }

    private void printFrames(final Frames frames) {
        List<Frame> elements = frames.elements();
        for (Frame frame : elements) {
            printFrame(frame);
        }
        System.out.println();
    }

    private void printFrame(final Frame frame) {
        Scores scores = frame.getScores();
        String scoresText = toScoresText(scores);

        if (scores.size() == 1) {
            System.out.print(changeNumberToCharacter(firstBall(scoresText)));
            return;
        }
        if (scores.size() == 2) {
            System.out.print(changeNumberToCharacter(secondBall(scoresText)));
            return;
        }
        if (scores.size() == 3) {
            System.out.print(changeNumberToCharacter(lastBall(scoresText)));
            return;
        }
        System.out.print("      |");
    }

    private String changeNumberToCharacter(String scoresText) {
        scoresText = scoresText.replaceAll("10", "X");
        scoresText = scoresText.replaceAll("0", "-");
        return scoresText;
    }

    private String firstBall(final String scoresText) {
        return String.format("  %s   |", scoresText);
    }

    private String secondBall(final String scoresText) {

        List<Integer> numbers = Arrays.stream(scoresText.split("\\|"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Score first = Score.valueOf(numbers.get(0));
        Score second = Score.valueOf(numbers.get(1));

        if (Score.isSpare(first, second)) {
            String[] scoreTexts = scoresText.split("\\|");
            return String.format("  %s |", scoreTexts[0] + "|" + "/");
        }
        return String.format("  %s |", scoresText);
    }

    private String lastBall(final String scoresText) {
        List<Integer> numbers = Arrays.stream(scoresText.split("\\|"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Score first = Score.valueOf(numbers.get(0));
        Score second = Score.valueOf(numbers.get(1));
        Score last = Score.valueOf(numbers.get(2));

        if (Score.isSpare(first, second)) {
            return String.format(" %s|", first.getNumberOfPins() + "|" + "/" + "|" + last.getNumberOfPins());
        }
        return String.format("  %s |", scoresText);
    }

    private String toScoresText(final Scores scores) {
        return scores.elements()
                .stream()
                .map(Score::getNumberOfPins)
                .map(String::valueOf)
                .collect(Collectors.joining("|"));
    }
}
