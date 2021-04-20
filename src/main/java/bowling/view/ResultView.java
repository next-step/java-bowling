package bowling.view;

import bowling.domain.Frame;
import bowling.domain.State;
import bowling.service.BowlingGame;

import java.io.PrintStream;
import java.util.stream.Stream;

public class ResultView {

    private static final String GUIDE_BOWLING_UI_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String GUIDE_BOWLING_BOARD_BODY_FORMATTER = "  %-4s|";
    private static final String WHITE_SPACE = "";
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";
    private final PrintStream out;

    public ResultView() {
        this.out = new PrintStream(System.out);
    }

    public void printBoard(String name) {
        printBoardHeader();
        out.printf("|  %s |      |      |      |      |      |      |      |      |      |      |", name);
        out.println();

    }

    public void printResult(BowlingGame bowling) {
        printBoardHeader();
        out.print("|  " + bowling.user() + " |");
        bowling.frames()
                .stream()
                .map(this::printCondition)
                .forEach(out::print);
        printEmptyBody(bowling);
        out.println();
    }

    private String printCondition(Frame frame) {
        int tryCount = frame.tryCount();
        if (tryCount == 2) { // SPARE, MISS
            return printValue(frame);
        }
        // 기본 출력
        return printState(frame);
    }

    private String printState(Frame frame) {
        if (frame.state().equals(State.GUTTER)) { // 1구 0점
            return printBoardBody("-" + "|");
        }
        // 1구 n점
        return printBoardBody(pin(frame, 0) + "|");
    }

    private String printValue(Frame frame) {
        if (frame.state().equals(State.STRIKE)) { // 1구 스트라이크
            return printBoardBody("X ");
        }
        if (frame.state().equals(State.SPARE)) { // 2구 스페어
            return printBoardBody(pin(frame, 0) + "|" + "/");
        }
        if (frame.state().equals(State.GUTTER)) { // 2구 거터
            return printBoardBody("-" + "|" + "-");
        }
        // 2구 미스
        return printBoardBody(pin(frame, 0) + "|" + pin(frame, 1));
    }

    private String pin(Frame frame, int index) {
        Integer integer = frame.pin(index);
        if (integer == 0) {
            return "-";
        }
        return String.valueOf(integer);
    }

    private void printEmptyBody(BowlingGame bowling) {
        Stream.generate(() -> printBoardBody(WHITE_SPACE))
                .limit(10 - bowling.frames.frameNumber())
                .forEach(out::print);
    }

    private void printBoardHeader() {
        out.println(GUIDE_BOWLING_UI_BOARD);
    }

    private String printBoardBody(String item) {
        return String.format(GUIDE_BOWLING_BOARD_BODY_FORMATTER, item);
    }
}
