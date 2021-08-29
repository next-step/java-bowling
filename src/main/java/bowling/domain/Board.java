package bowling.domain;

import bowling.domain.state.State;
import java.util.List;

public class Board {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String SPACE = " ";
    private static final String BOUNDARY_LINE = "|";
    private static final String INIT_FRAME = "      "+ BOUNDARY_LINE;

    private static final int MAX_FRAME = 10;
    private static final int MIN_PIN = 0;

    private final List<String> boards;

    public Board(List<String> boards) {
        initFrame(boards);
        this.boards = boards;
    }

    private void initFrame(List<String> boards) {
        for (int i = 0; i < MAX_FRAME; i++) {
            boards.add(INIT_FRAME);
        }
    }

    public void addFrame(int frameNum, State state) {
        boards.set(frameNum, spareOrMiss(state));
    }

    public void addStrike(int frameNum) {
        boards.set(frameNum, isStrike());
    }

    public void addLast(int frameNum, String state) {
        boards.set(frameNum, SPACE + state);
    }

    public List<String> getBoards() {
        return boards;
    }

    private String isStrike() {
        return "   " + STRIKE + "  " + BOUNDARY_LINE;
    }

    public String spareOrMiss(State state) {
        String pins = gutterCheck(state.firstPins());

        if ((state.firstPins() + state.secondPins()) == 10) {
            return "  " + pins + BOUNDARY_LINE + SPARE + SPACE + BOUNDARY_LINE;
        }
        return "  " + pins + BOUNDARY_LINE + gutterCheck(state.secondPins()) + SPACE + BOUNDARY_LINE;
    }

    public String lastStrike() {
        return STRIKE + BOUNDARY_LINE;
    }

    public String lastSpareOrMiss(State state) {
        String pins = gutterCheck(state.firstPins());

        if ((state.firstPins() + state.secondPins()) == 10) {
            return pins + BOUNDARY_LINE + SPARE + BOUNDARY_LINE;
        }
        return SPACE + pins + BOUNDARY_LINE + gutterCheck(state.secondPins()) + SPACE + BOUNDARY_LINE;
    }

    private String gutterCheck(int falledPins) {
        if (falledPins == MIN_PIN) {
            return GUTTER;
        }
        return String.valueOf(falledPins);
    }

}
