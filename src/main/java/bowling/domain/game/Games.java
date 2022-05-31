package bowling.domain.game;

import bowling.domain.frame.FrameNumber;
import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Games {
    private final List<Game> games;
    private Turn turn;

    public Games(List<Game> games) {
        this(games, Turn.initialize(games.size()));
    }

    public Games(List<Game> games, Turn turn) {
        validate(games, turn);
        this.games = games;
        this.turn = turn;
    }

    private void validate(List<Game> games, Turn turn) {
        if (games == null || CollectionUtils.isEmpty(games)) {
            throw new IllegalArgumentException("games는 빈 값일 수 없습니다.");
        }

        if (turn == null) {
            throw new IllegalArgumentException("turn은 빈 값일 수 없습니다.");
        }
    }

    public void bowl(Pin pin) {
        current().bowl(pin);

        if (isTurnOver()) {
            turnOver();
        }
    }

    private boolean isTurnOver() {
        Game current = current();
        Game next = next();

        if (current.isDone()) {
            return true;
        }

        if (next.isDone()) {
            return false;
        }

        FrameNumber currentGameFrame = current.currentFrameNumber();
        FrameNumber nextGameFrame = next.currentFrameNumber();

        return currentGameFrame.isBigger(nextGameFrame) || (isLast() && currentGameFrame.equals(nextGameFrame));
    }

    private void turnOver() {
        turn = turn.next();
    }

    private boolean isLast() {
        return turn.isLast();
    }

    private Game current() {
        return games.get(turn.index());
    }

    private Game next() {
        return games.get(turn.next().index());
    }

    private Game last() {
        return games.get(games.size() - 1);
    }

    public Player whoseTurn() {
        return current().player();
    }

    public boolean isDone() {
        return last().isDone();
    }


    public List<Game> getGames() {
        return games;
    }
}
