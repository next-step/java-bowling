package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.state.Strike;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String VERTICAL = "|";
    private static final String NAME = "NAME";
    private static final String SCORE_BOARD_TOP;
    private static final String EMPTY_FRAME = "|      ";
    private static final String STRIKE_STATE = "  X   ";
    public static final String MISS_STATE = "%3d|%d ";
    private static final String GUTTER_STATE = " -|-  ";
    public static final String FIRST_BLOW_STATE = "%3d   ";
    private static final String SPARE_STATE = "%3d|/ ";

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

    private static String printGutter(Pins pins) {
        if (pins.getFirstDownPin() == 0) {
            return GUTTER_STATE;
        }
        return String.format("%3d|-  ", pins.getFirstDownPin());
    }

    private static String printSpare(Pins pins) {
        return String.format(SPARE_STATE, pins.getFirstDownPin());
    }

    public static String printFirstBlow(Pins pins) {
        if (pins.getFirstDownPin() == 0) {
            return "  -  ";
        }
        return String.format(FIRST_BLOW_STATE, pins.getFirstDownPin());
    }

    public static String printMiss(Pins pins) {
        if (pins.getFirstDownPin() == 0) {
            return String.format(" -|%d  ", pins.getSecondDownPin());
        }

        if (pins.getSecondDownPin() == 0) {
            return String.format("  %d|- ", pins.getFirstDownPin());
        }

        return String.format(MISS_STATE, pins.getFirstDownPin(), pins.getSecondDownPin());
    }

    private static String printScore(Pins pins) {
        if (pins.isStrike()) {
            return STRIKE_STATE;
        }

        if (pins.isSpare()) {
            return printSpare(pins);
        }

        if (pins.isMiss()) {
            return printMiss(pins);
        }

        if (pins.isGutter()) {
            return printGutter(pins);
        }

        if (pins.isRunning()) {
            return printFirstBlow(pins);
        }

        throw new BowlingException();
    }

    private static void printNormalFrame(Frame frame) {
        while (frame != null && frame instanceof NormalFrame) {


            String result = Optional.ofNullable(frame.getPins().get(0))
                    .map(pins -> printScore(pins))
                    .orElse(EMPTY_FRAME);


            System.out.print(String.format("%s%s", VERTICAL, result));

            frame = frame.getNext();
        }
    }

    private static void printFinalFrame(Frame frame) {
        if (frame == null || !(frame instanceof FinalFrame)) {
            return;
        }

        if (frame instanceof Strike) {
            System.out.print(STRIKE_STATE);
            return;
        }

//        System.out.print(String.format("%s%s", VERTICAL,
//                frame.getState().getCurrentPinsState()));
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

    private static void printScore(Score score) {
        if (score != null) {
            System.out.print(String.format("%s%4d  ",
                    VERTICAL, score.getScore()));
        } else {
            System.out.print(EMPTY_FRAME);
        }
    }

    public static void printScorePlayer(BowlingGame bowlingGame, int frameNumber) {
        System.out.print(EMPTY_FRAME + "  ");

        for (int i = 1; i <= frameNumber; i++) {
            printScore(bowlingGame.getTotalScore(i));
        }

        printEmptyFrame(9 - frameNumber);
        System.out.println();
    }
}
