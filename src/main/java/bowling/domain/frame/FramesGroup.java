package bowling.domain.frame;

import bowling.domain.player.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class FramesGroup {

    private static final int FINISHED_CRITERIA_COUNT = 0;

    private final Map<Player, Frames> framesGroup;

    private FramesGroup(final Map<Player, Frames> framesGroup) {
        this.framesGroup = framesGroup;
    }

    public static FramesGroup of(final Map<Player, Frames> framesGroup) {
        return new FramesGroup(framesGroup);
    }

    public static FramesGroup of(final List<String> playerNames) {
        HashMap<Player, Frames> framesGroup = new LinkedHashMap<>();
        for (String playerName : playerNames) {
            framesGroup.put(Player.valueOf(playerName), Frames.of());
        }
        return new FramesGroup(framesGroup);
    }

    public void inputNumber(int number) {
        nextFrames().add(number);
    }

    public Frames nextFrames() {
        return nextTurn().getValue();
    }

    public Player nextPlayer() {
        return nextTurn().getKey();
    }

    private Map.Entry<Player, Frames> nextTurn() {
        return framesGroup.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isFinished() {
        return notFinishedFramesCount() == FINISHED_CRITERIA_COUNT;
    }

    private int notFinishedFramesCount() {
        return (int) framesGroup.values().stream()
                .filter(frames -> !frames.isFinished())
                .count();
    }

    public Map<Player, Frames> getFramesGroup() {
        return framesGroup;
    }
}