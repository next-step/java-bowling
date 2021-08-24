package bowling.domain;

import bowling.domain.state.State;
import java.util.List;

public class Board {
    private final List<String> boards;

    public Board(List<String> boards) {
        initFrame(boards);
        this.boards = boards;
    }

    private void initFrame(List<String> boards) {
        for (int i = 0; i < 10; i++) {
            boards.add("     |");
        }
    }

    public void addFrame(int frameNum, State state) {
        boards.set(frameNum, state.display());
    }

    public void addLast(int frameNum, String state) {
        boards.set(frameNum, state);
    }

    @Override
    public String toString() {
        return boards.toString().replaceAll("\\[|\\]", "")
                                .replaceAll(", "," ");
    }
}
