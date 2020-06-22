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

    public PlayerFrames lastValue(NormalFrame normalFrame) {
        return new PlayerFrames(Collections.singletonList(normalFrame));
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
}
