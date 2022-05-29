package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Boards {
    private final List<Board> boards;
    private Board current;

    public Boards(List<String> names) {
        this.boards = names.stream()
                .map(Board::new)
                .collect(Collectors.toList());
        current = boards.get(0);
    }

    public void bowl(int pins) {
        int beforeFrameNumber = current.currentFrameNumber();
        int afterFrameNumber = current.bowl(pins);

        if (beforeFrameNumber == 10 && current.isGameEnd()) {
            changeCurrentBoard();
        }

        if (beforeFrameNumber != afterFrameNumber) {
            changeCurrentBoard();
        }
    }

    private void changeCurrentBoard() {
        int index = boards.indexOf(current);
        current = boards.get((index + 1) % boards.size());
    }

    public String name() {
        return current.name();
    }

    public List<Board> boards() {
        return Collections.unmodifiableList(boards);
    }

    public boolean isGameEnd() {
        return boards.size() == boards.stream().filter(Board::isGameEnd).count();
    }
}
