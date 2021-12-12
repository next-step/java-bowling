package bowling.domain.result;

import bowling.domain.user.User;

import java.util.Objects;

public class GameResult {
    private final User user;
    private final FrameResults results;

    public GameResult(User user, FrameResults results) {
        this.user = user;
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResult that = (GameResult) o;
        return Objects.equals(user, that.user) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, results);
    }
}
