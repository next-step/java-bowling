package bowling.domain;

import java.util.List;

public class Game {
    private static final int FIRST_FRAME_NUMBER = 1;
    private String userName;
    private Board board;

    public Game(String userName) {
        this.userName = userName;
    }

    public Game(String userName, Board board) {
        this.userName = userName;
        this.board = board;
    }

    public Frame startGame() {
        NormalFrame normalFrame = new NormalFrame(FIRST_FRAME_NUMBER);
        return normalFrame;
    }

    public String getUserName() {
        return userName;
    }

    public Board getBoard() {
        return board;
    }

    public List<FrameResult> getFrameResult() {
        return board.getFrameResults();
    }
}
