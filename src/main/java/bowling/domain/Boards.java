package bowling.domain;

import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Boards {

    private final List<Board> boards;
    private int currentBoardIndex;

    private Boards(List<Board> boards) {
        this.boards = boards;
    }

    public static Boards of(List<Board> boards) {
        return new Boards(boards);
    }

    public boolean isAllBoardFinished() {
        return boards.stream()
                .allMatch(Board::isAllFrameFinished);
    }

    public Board getCurrentBoard() {
        return boards.get(currentBoardIndex);
    }

    public List<Board> getContent() {
        return Collections.unmodifiableList(boards);
    }

    public void addScore(Score score) {
        Board currentBoard = getCurrentBoard();
        int beforeFrameIndex = currentBoard.getCurrentFrameIndex();

        currentBoard.addScore(score);
        int afterFrameIndex = currentBoard.getCurrentFrameIndex();

        adjustCurrentFrameIndex(beforeFrameIndex, afterFrameIndex);
    }

    private void adjustCurrentFrameIndex(int beforeFrameIndex, int afterFrameIndex) {
        if (beforeFrameIndex == afterFrameIndex) {
            return;
        }

        if (currentBoardIndex >= boards.size() - 1) {
            currentBoardIndex = 0;
            return;
        }

        currentBoardIndex++;
    }
}
