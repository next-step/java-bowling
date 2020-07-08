package bowling.domain;

import bowling.domain.state.State;

import java.util.*;
import java.util.stream.Stream;

public class MultiUserFrames {
    private final List<Player> players;
    private final Queue<Frames> multiframes;

    public MultiUserFrames() {
        this.players = new ArrayList<>();
        this.multiframes = new LinkedList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        multiframes.add(new Frames(player));
    }

    public State bowling(Pin nextPin) {
        Frames currentFrames = multiframes.peek();
        State state = currentFrames.bowling(nextPin);
        if (state.isNew() || state.isFinish()) {
            multiframes.poll();
            multiframes.add(currentFrames);
        }
        return state;
    }

    public String getCurrentPlayerName() {
        return multiframes.peek().getPlayerName();
    }

    public Stream<Frames> forEachFrames() {
        return multiframes
                .stream()
                .sorted(Comparator.comparingInt(Frames::getPlayerId));
    }

    public boolean isGameEnd() {
        return multiframes
                .stream()
                .allMatch(Frames::isGameEnd);
    }
}
