package bowling.step4.domain;

import bowling.step4.domain.frame.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayersFrames {
    private final List<PlayerFrames> playersFrames;

    private PlayersFrames(List<PlayerFrames> playersFrames) {
        this.playersFrames = playersFrames;
    }

    public static PlayersFrames of(List<PlayerFrames> playersFrames) {
        return new PlayersFrames(playersFrames);
    }

    public static PlayersFrames init(Players players) {
        return players.stream()
                      .map(player -> PlayerFrames.of(player, NormalFrame.start()))
                      .collect(collectingAndThen(toList(), PlayersFrames::of));
    }

    public Stream<PlayerFrames> stream() {
        return playersFrames.stream();
    }

    public boolean isLast() {
        return playersFrames.stream()
                            .map(PlayerFrames::getLastFrame)
                            .allMatch(frame -> frame instanceof FinalFrame);
    }
}