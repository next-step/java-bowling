package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.factory.FrameFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingClub {
    private final List<BowlingGame> bowlingGames;

    private BowlingClub(List<Player> players, FrameFactory frameFactory) {
        this.bowlingGames = players.stream()
                .map(player -> BowlingGame.of(Frames.from(frameFactory.create()), player))
                .collect(Collectors.toList());
    }

    public static BowlingClub of(List<Player> players, FrameFactory frameFactory) {
        return new BowlingClub(players, frameFactory);
    }

    public boolean isGameOver() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isGameOver);
    }

    @GetterForUI
    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(new ArrayList<>(bowlingGames));
    }
}
