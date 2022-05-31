package bowling.view;

import bowling.domain.Frame;
import bowling.domain.LastFrame;
import bowling.domain.NormalFrame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final List<String> FRAMES = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private ResultView() {
    }

    public static void printBeforeGame(String name) {
        printLabel();
        printSymbol(name, new ArrayList<Frame>());
        printScore(accumulateScore(new ArrayList<String>()));
    }

    public static void printGameInProgress(String name, List<Frame> records, List<String> scores) {
        printLabel();
        printSymbol(name, records);
        printScore(accumulateScore(scores));
    }

    private static void printLabel() {
        System.out.print("| NAME |");
        FRAMES.stream()
                .forEach(frame -> System.out.print("\t" + frame + "\t|"));
        System.out.println();
    }

    private static void printSymbol(String name, List<Frame> records) {
        System.out.print("|  " + name + " |");
        records.stream()
                .forEach(frame -> System.out.print("\t" + symbol(frame) + "\t|"));
        printBlank(records);
        System.out.println();
    }

    private static String symbol(Frame frame) {
        if (frame instanceof LastFrame) {
            return symbolIfLastFrame((LastFrame) frame);
        }
        return symbolIfNormalFrame((NormalFrame) frame);
    }

    private static String symbolIfNormalFrame(NormalFrame frame) {
        if (frame.firstOfFrame()) {
            return frame.firstState().symbol();
        }
        return frame.firstState().symbol() + frame.secondState().symbol();
    }

    private static String symbolIfLastFrame(LastFrame frame) {
        if (frame.firstOfFrame()) {
            return frame.firstState().symbol();
        }
        if (frame.secondOfFrame()) {
            return frame.firstState().symbol() + frame.secondState().symbol();
        }
        return frame.firstState().symbol() + frame.secondState().symbol() + ((LastFrame) frame).thirdState().symbol();
    }

    private static <E> void printBlank(List<E> list) {
        int remains = FRAMES.size() - list.size();
        if (remains != 0) {
            IntStream.range(0, remains)
                    .forEach((remain -> System.out.print("\t\t|")));
        }
    }

    public static void printScore(List<String> scores) {
        System.out.print("|      |");
        scores.stream()
                .forEach(score -> System.out.print("\t" + score + "\t|"));
        printBlank(scores);
        System.out.println();
    }

    private static List<String> accumulateScore(List<String> scores) {
        List<Integer> cumulativeScores = new ArrayList<>();
        List<Integer> _scores = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            String score = scores.get(i);
            if (score == "") {
                break;
            }
            _scores.add(i, Integer.parseInt(scores.get(i)));
            cumulativeScores.add(i, accumulate(_scores));
        }
        return cumulativeScores.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private static int accumulate(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }
}
