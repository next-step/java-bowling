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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user)
                .append(frames)
                .append(frames.scores())
                .append("\n");
        return stringBuilder.toString();
    }
}
