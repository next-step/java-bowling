package bowling.domain;

import bowling.domain.state.State;
import java.util.List;

public class Board {
    private static final int MAX_FRAME = 10;
    private static final String SPACE = " ";
    private static final String INIT_FRAME = "      |";

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
        boards.set(frameNum, SPACE + state.display());
    }

    public void addLast(int frameNum, String state) {
        boards.set(frameNum, SPACE + state);
    }

    public List<String> getBoards() {
        return boards;
    }


}
