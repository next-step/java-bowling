package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Index;
import bowling.domain.frame.ScoreBoard;
import bowling.domain.pin.Pin;
import bowling.domain.user.User;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGame {

    private final List<ScoreBoard> game;

    private BowlingGame(List<ScoreBoard> game) {
        this.game = game;
    }

    public static BowlingGame of(List<String> users) {
        return users.stream()
                .map(User::of)
                .map(user -> ScoreBoard.init(user, Frames.init()))
                .collect(collectingAndThen(toList(), BowlingGame::new));
    }

    public void bowl(Pin felled) {
        getCurrent().bowl(felled);
    }

    public List<ScoreBoard> getGame() {
        return game;
    }

    public ScoreBoard getCurrent() {
        return game.stream()
                .filter(scoreBoard -> !scoreBoard.isGameOver())
                .min(Comparator.comparing(ScoreBoard::getLastIndex, Comparator.comparingInt(Index::getIndex)))
                .orElseThrow(() -> new IllegalArgumentException("모든 게임이 종료 되었습니다."));
    }

    public boolean isGameOver() {
        return game.stream()
                .map(ScoreBoard::isGameOver)
                .reduce((b1, b2) -> b1 && b2)
                .orElseThrow(IllegalAccessError::new);
    }

    public User getCurrentUser() {
        return getCurrent().getUser();
    }
}
