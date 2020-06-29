package bowling.domain;

import java.util.List;

public class Player {
    private final String name;
    private final Frames frames;

    public Player(String name, int finalRound) {
        this.name = name;
        this.frames = new Frames(finalRound);
    }

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public List<List<ResultType>> getGameResult() {
        return frames.getGameResult();
    }

    public int getCurrentRound() {
        return frames.getCurrentRound();
    }
}
