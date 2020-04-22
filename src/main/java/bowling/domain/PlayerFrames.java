package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerFrames implements Iterable<PlayerFrame> {
    private List<PlayerFrame> playerFrames;

    public PlayerFrames(List<String> playerName) {
        playerFrames = playerName.stream()
                .map(PlayerFrame::new)
                .collect(Collectors.toList());
    }

    public PlayerFrames(PlayerFrames playerFrames) {
        this.playerFrames = new ArrayList<>(playerFrames.playerFrames);
    }

    public boolean addPinCount(int index, int pinCount) {
        return playerFrames.get(index).addPinCount(pinCount);
    }

    public boolean isDone(int index) {
        return playerFrames.get(index).isCurrentFrameDone();
    }

    public String getPlayerName(int index) {
        return playerFrames.get(index).getPlayerName();
    }

    public boolean isFinished() {
        return playerFrames.stream().allMatch(PlayerFrame::isFinished);
    }

    public int size() {
        return playerFrames.size();
    }

    public Stream<PlayerFrame> stream() {
        return playerFrames.stream();
    }

    @Override public Iterator<PlayerFrame> iterator() {
        return playerFrames.iterator();
    }
}
