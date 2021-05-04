package bowling.domain.view;

import bowling.domain.Player;
import bowling.domain.dto.FrameResponse;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Miss;
import bowling.domain.state.PitchState;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static List<FrameResponse> frameNormalResponses = new ArrayList<>();
    private static List<FrameResponse> frameFinalResponses = new ArrayList<>();

    public static String DIVISION = "|";
    public static String GUTTER = "-";
    public static String STRIKE = "X";
    public static String SPARE = "/";
    public static int MAX_FRAME = 10;
    public static int MIN_FRAME = 1;


    public static void printBowlingResult(Player player, FrameResponse frameResponse) {
        addFrameResponse(frameResponse);

        printRound();
        printResult(player.getName());
    }

    private static void addFrameResponse(FrameResponse frameResponse) {
        Frame frame = frameResponse.getFrame();
        boolean next = frame.getPitchState().isNext();

        addFrameNormalResponse(frameResponse, next);
        addFrameFinalResponse(frameResponse, frame);
    }

    private static void addFrameNormalResponse(FrameResponse frameResponse, boolean next) {
        if ((next || frameResponse.getFrame().getPitchState() instanceof Strike)
                && frameResponse.getFrame() instanceof NormalFrame) {
            frameNormalResponses.add(frameResponse);
        }
    }

    private static void addFrameFinalResponse(FrameResponse frameResponse, Frame frame) {
        if (frame instanceof FinalFrame) {
            frameFinalResponses.add(frameResponse);
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
                .map(frameResponse -> {
                    Frame frame = frameResponse.getFrame();
                    PitchState pitchState = frame.getPitchState();

                    if (pitchState instanceof Strike) {
                        return "  " + STRIKE + "   " + DIVISION;
                    }

                    if (pitchState instanceof Spare) {
                        return "  " + pitchState.getFirstPoint() + DIVISION + SPARE + " " + DIVISION;
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
                .map(frameResponse -> {
                    Frame frame = frameResponse.getFrame();
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

                    if (size == 2) {
                        return printGutter(pitchState.getFirstPoint());
                    }

                    return printGutter(pitchState.getFirstPoint());

                })
                .collect(Collectors.joining(DIVISION));
        System.out.print(collect);

        printFinalLeftOver(size);
        System.out.println();
    }

    private static void printFinalLeftOver(int size) {
        String blank = printBlank(size);
        System.out.print(blank + DIVISION);
    }

    private static String printBlank(int size) {
        return IntStream.range(size * 2, 6)
                .mapToObj(index -> " ")
                .collect(Collectors.joining(""));
    }

    private static void printName(String name) {
        System.out.print(DIVISION + "  " + name + "   " + DIVISION);
    }

    private static String printGutter(int pitchState) {
        if (pitchState == 0) {
            return GUTTER;
        }

        return String.valueOf(pitchState);
    }
}
