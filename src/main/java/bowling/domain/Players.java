package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameLinkedList;
import bowling.domain.frame.Frames;

import java.util.Map;

public class Players {
    private Map<Player, Frames> players;

    public Players(Map<Player, Frames> players) {
        this.players = players;
    }


    public Map<Player, Frames> getPlayers() {
        return players;
    }

    private Frames getFrames(Player player) {
        return players.get(player);
    }

    public FrameLinkedList getFrameLinkedList(Player player) {
        return getFrames(player).getFrames();
    }

    public Frame getFrame(Player player, int frameIndex) {
        return getFrames(player).getFrame(frameIndex);
    }
}