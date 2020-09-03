package camp.nextstep.edu.rebellion.bowling.domain;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final Player player;
    private final Frames frames;

    public ScoreBoard(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List<String> getResultSymbol() {
        return this.frames
                .getFrames()
                .stream()
                .map(f -> f.getStatus().makeSymbol())
                .collect(Collectors.toList());
    }
}
