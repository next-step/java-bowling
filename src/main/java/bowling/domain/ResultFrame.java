package bowling.domain;

import bowling.vo.GameUser;

import java.util.HashMap;
import java.util.Map;

public class ResultFrame {
    private final GameUser gameUser;
    private final Map<Integer, Frame> snapshots = new HashMap<>();

    public ResultFrame(final GameUser gameUser) {
        this.gameUser = gameUser;
    }

    public void record(final Integer frameOrder, final Frame frame) {
        this.snapshots.put(frameOrder, frame);
    }

    public int count() {
        return snapshots.size();
    }

    public Frame getFrame(final Integer frameOrder) {
        return snapshots.get(frameOrder);
    }

    public GameUser getGameUser() {
        return gameUser;
    }

    @Override
    public String toString() {
        return "ResultFrame{" +
                "gameUser=" + gameUser +
                ", snapshots=" + snapshots +
                '}';
    }
}
