package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerFrames {
    private final List<Frame> playerFrameList;

    public PlayerFrames(List<Frame> playerFrameList) {
        this.playerFrameList = playerFrameList;
    }

    public static PlayerFrames createEmpty() {
        return new PlayerFrames(new ArrayList<>());
    }

    public int size() {
        return this.playerFrameList.size();
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
