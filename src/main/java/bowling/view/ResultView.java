package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.state.Miss;
import bowling.domain.state.PitchState;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static List<Frame> frameNormalResponses = new ArrayList<>();
    private static List<Frame> frameFinalResponses = new ArrayList<>();

    public static final String DIVISION = "|";
    public static final String GUTTER = "-";
    public static final String STRIKE = "X";
    public static final String SPARE = "/";
    public static final int MAX_FRAME = 10;
    public static final int MIN_FRAME = 1;


    public static void printBowlingResult(Player player, Frame frame) {
        addFrameResponse(frame);

        printRound();
        printResult(player.getName());
    }

    private static void addFrameResponse(Frame frame) {
        boolean next = frame.getPitchState().isNext();

        addFrameNormalResponse(frame, next);
        addFrameFinalResponse(frame);
    }

    private static void addFrameNormalResponse(Frame frame, boolean next) {
        if ((next || frame.getPitchState() instanceof Strike)
                && frame.isNormalRound() && !frameNormalResponses.contains(frame)) {
                frameNormalResponses.add(frame);
        }
    }

    private static void addFrameFinalResponse(Frame frame) {
        if (!frame.isNormalRound()) {
            frameFinalResponses.add(frame);
        }
    }


    public static void printRound() {
        printName();
        for (int i = MIN_FRAME; i < MAX_FRAME + 1; i++) {
            System.out.print("  " + DIVISION + "  " + (i < MAX_FRAME ? "0" + i : i));
        }

        System.out.println("  " + DIVISION);
    }

    private static void printName() {
        System.out.print(DIVISION + "  NAME");
    }

    public static void printResult(String name) {
        printName(name);
        printNormalRoundResult();
        printFinalRoundResult();
    }

    private static void printNormalRoundResult() {
        frameNormalResponses.stream()
                .map(frame -> {
                    PitchState pitchState = frame.getPitchState();

                    if (pitchState instanceof Strike) {
                        return "  " + STRIKE + "   " + DIVISION;
                    }

                    if (pitchState instanceof Spare) {
                        return "  " +  printGutter(pitchState.getFirstPoint()) + DIVISION + SPARE + " " + DIVISION;
                    }

                    if (pitchState instanceof Miss) {
                        return " " + printGutter(pitchState.getFirstPoint())
                                + DIVISION + printGutter(pitchState.getSecondPoint())
                                + "  " + DIVISION;
                    }

                    return "  " + printGutter(pitchState.getFirstPoint()) + "   " + DIVISION;
                })
                .forEach(System.out::print);

        printNormalLeftOver(frameNormalResponses.size());
    }

    private static void printNormalLeftOver(int count) {
        for (int i = count + 1; i < MAX_FRAME; i++) {
            System.out.print("      " + DIVISION);
        }
    }

    private static void printFinalRoundResult() {
        int size = frameFinalResponses.size();

        String collect = frameFinalResponses.stream()
                .map(frame -> {
                    PitchState pitchState = frame.getPitchState();
                    if (pitchState instanceof Strike) {
                        return STRIKE;
                    }

                    if (pitchState instanceof Spare) {
                        return SPARE;
                    }

                    if (pitchState instanceof Miss) {
                        return printGutter(pitchState.getSecondPoint());
                    }

                    if (size == 2 || size == 3) {
                        return printGutter(pitchState.getFirstPoint());
                    }

                    return printGutter(pitchState.getFirstPoint());

                })
                .collect(Collectors.joining(DIVISION));

        System.out.print(" " + collect);
        printFinalLeftOver(size);
        System.out.println();
    }

    private static void printFinalLeftOver(int size) {
        String blank = printBlank(size);
        System.out.print(blank + DIVISION);
    }

    private static String printBlank(int size) {
        if (size == 0) {
            return "     ";
        }

        return IntStream.range(size * 2, 6)
                .mapToObj(index -> " ")
                .collect(Collectors.joining(""));
    }

    private static void printName(String name) {
        System.out.print(DIVISION + "  " + name + "   " + DIVISION);
    }

    private static String printGutter(int point) {
        if (point == 0) {
            return GUTTER;
        }

        return String.valueOf(point);
    }
}
