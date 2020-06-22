package bowling.domain.frame;

import java.util.*;

public class PlayerFrames {
    private final List<Frame> playerFrameList;

    PlayerFrames(List<Frame> playerFrameList) {
        this.playerFrameList = new ArrayList<>(playerFrameList);
    }

    public static PlayerFrames createEmpty() {
        return new PlayerFrames(new ArrayList<>());
    }

    public int size() {
        return this.playerFrameList.size();
    }

    public PlayerFrames lastValue(Frame frame) {
        if (this.playerFrameList.size() == 0) {
            return new PlayerFrames(Collections.singletonList(frame));
        }

        if (this.playerFrameList.get(this.playerFrameList.size() - 1).isCompleted()) {
            ArrayList<Frame> resultFrames = new ArrayList<>(playerFrameList);
            resultFrames.add(frame);

            return new PlayerFrames(resultFrames);
        }

        ArrayList<Frame> resultFrames = new ArrayList<>(playerFrameList);
        resultFrames.set(this.playerFrameList.size() - 1, frame);

        return new PlayerFrames(resultFrames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerFrames that = (PlayerFrames) o;
        return Objects.equals(playerFrameList, that.playerFrameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerFrameList);
    }

    @Override
    public String toString() {
        return "PlayerFrames{" +
                "playerFrameList=" + playerFrameList +
                '}';
    }
}
