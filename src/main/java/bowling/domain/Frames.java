package bowling.domain;

import java.util.LinkedList;

public class Frames {
    private Player player;
    private LinkedList<Frame> finalFrame;

    public Frames(Player player, LinkedList<Frame> finalFrame) {
        this.player = player;
        this.finalFrame = finalFrame;
    }

    public static Frames getDefault(Player player, LinkedList<Frame> finalFrame) {

        return new Frames(player, finalFrame);
    }
}
