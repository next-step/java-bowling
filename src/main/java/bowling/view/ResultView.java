package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String VERTICAL = "|";
    private static final String NAME = "NAME";
    private static final String SCORE_BOARD_TOP;
    private static final String EMPTY_FRAME = "|      ";

    static {
        String buffer = IntStream.range(1, 11)
                .mapToObj(i -> String.format("%-3s%02d  ", VERTICAL, i))
                .collect(Collectors.joining("", String.format("%-3s%s  ", VERTICAL, NAME), VERTICAL));

        SCORE_BOARD_TOP = buffer;
    }

    public static void printScoreBoardTop() {
        System.out.println(SCORE_BOARD_TOP);
    }

    private static void printPlayerName(String name) {
        System.out.print(String.format("%-3s%s   ", VERTICAL, name));
    }

    private static void printNormalFrame(Frame frame) {
        while (frame != null && frame instanceof NormalFrame) {
            System.out.print(String.format("%s%s", VERTICAL,
                    frame.getState().getCurrentPinsState()));

            frame = frame.getNext();
        }
    }

    private static void printFinalFrame(Frame frame) {
        if (frame == null || !(frame instanceof FinalFrame)) {
            return;
        }

        System.out.print(String.format("%s%s", VERTICAL,
                frame.getState().getCurrentPinsState()));
    }

    private static void printEmptyFrame(int count) {
        for (int i = 0; i < count + 2; i++) {
            System.out.print(EMPTY_FRAME);
        }
    }


    public static void printScoreBoardPlayer(BowlingGame bowlingGame) {
        printPlayerName(bowlingGame.getPlayerName());

        Frame frame = bowlingGame.getFirstFrame();
        printNormalFrame(frame);

        Frame finalFrame = bowlingGame.getCurrentFrame();
        printFinalFrame(finalFrame);
        printEmptyFrame(Frame.MAX_FRAME_NUMBER - bowlingGame.getFrameSize());
        System.out.println();
    }
}
