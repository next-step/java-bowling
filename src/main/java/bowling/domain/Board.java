package bowling.domain;

import bowling.domain.frame.Frames;

public class Board {
    private final Frames frames;
    private final User user;

    public Board(String name) {
        frames = new Frames();
        user = new User(name);
    }

    public int bowl(int pins) {
        return frames.bowl(pins);
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }

    public String mark() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.mark())
                .append(frames.mark())
                .append("\n");
        return stringBuilder.toString();
    }
}
