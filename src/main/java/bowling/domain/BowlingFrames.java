package bowling.domain;

import java.util.List;

public class BowlingFrames {

    private List<NomalFrame> nomalFrames;

    private final Player player;

    public BowlingFrames(List<NomalFrame> nomalFrames, Player player) {
        this.nomalFrames = nomalFrames;
        this.player = player;
    }

    public static BowlingFrames of(List<NomalFrame> nomalFrames, Player player) {
        return new BowlingFrames(nomalFrames, player);
    }

    public List<NomalFrame> getNomalFrames() {
        return nomalFrames;
    }



}
