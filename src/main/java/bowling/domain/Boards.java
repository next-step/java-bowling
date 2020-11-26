package bowling.domain;

import java.util.LinkedList;
import java.util.List;

public class Boards {
    private final List<Board> boards = new LinkedList<>();

    public void add(Board board) {
        boards.add(board);
    }
}
