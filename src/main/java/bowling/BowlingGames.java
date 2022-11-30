package bowling;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames start(List<UserName> userNames) {
        List<BowlingGame> games = userNames.stream()
            .map(i -> new BowlingGame(i, Frames.start()))
            .collect(Collectors.toList());
        return new BowlingGames(games);
    }

    public boolean isFinished() {
        return bowlingGames.stream()
            .allMatch(BowlingGame::isFinished);
    }

    public void bowl(Pin falledPins) {
        currentTurnOfUser().bowl(falledPins);
    }

    public BowlingGame currentTurnOfUser() {
        return bowlingGames.stream()
            .filter(user -> !user.isFinished())
            .min(Comparator.comparingInt(BowlingGame::currentFrameNumber))
            .orElseThrow(IllegalStateException::new);
    }

    public List<BowlingGame> getValues() {
        return bowlingGames;
    }
}
