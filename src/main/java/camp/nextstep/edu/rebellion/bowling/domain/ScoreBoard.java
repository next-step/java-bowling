package camp.nextstep.edu.rebellion.bowling.domain;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final Player player;
    private final List<Frame> frames;

    public ScoreBoard(Player player, List<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List<String> getResultSymbol() {
        return this.frames
                .stream()
                .map(f -> f.getStatus().makeSymbol())
                .collect(Collectors.toList());
    }
}
