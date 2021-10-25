package bowling.model;

import java.util.LinkedList;
import java.util.Objects;

import bowling.model.frame.Board;
import bowling.model.frame.Frame;

public class Player {
    private final String username;
    private LinkedList<Frame> frames = new LinkedList<>();

    public Player(String username, Frame frame) {
        isValid(username);
        this.username = username.trim();
        this.frames.add(frame);
    }

    public String getName() {
        return username;
    }

    private void isValid(String name) {
        if (!name.trim().matches("[A-Za-z]{3}")) {
            throw new IllegalArgumentException("영문으로 3자 입력해주세요.");
        }
    }

    public boolean bowl(int falledPins) {
        Frame currentFrame = getCurrentFrame();
        Frame nextFrame = currentFrame.bowl(falledPins);
        frames.add(nextFrame);

        return currentFrame.getNo() < nextFrame.getNo();
    }

    public Board createBoard() {
        return frames.getFirst().createBoard();
    }

    public Frame getCurrentFrame() {
        return frames.getLast();
    }

    public boolean isEndGame() {
        return getCurrentFrame().isEndGame();
    }

    public int getFrameNo() {
        return getCurrentFrame().getNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return username.equals(player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
