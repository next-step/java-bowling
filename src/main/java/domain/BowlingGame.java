package domain;

import java.util.List;

public class BowlingGame {

    private Player player;
    private List<NormalFrame> normalFrames;
    private FinalFrame finalFrame;

    private BowlingGame(Player player) {
        this.player = player;
    }

    public static BowlingGame from(Player player) {
        return new BowlingGame(player);
    }
}
