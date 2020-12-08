package step4.domain;

import java.util.Collections;
import java.util.List;

public class GameHistory {
    private final String playerName;
    private final List<String> marks;
    private final List<String> points;

    public GameHistory(Builder builder) {
        this.marks = builder.marks;
        this.points = builder.points;
        this.playerName = builder.playerName;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public List<String> getMarks() {
        return marks;
    }

    public List<String> getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static class Builder {
        private String playerName;
        private List<String> marks;
        private List<String> points;

        public Builder playerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder marks(List<String> marks) {
            this.marks = Collections.unmodifiableList(marks);
            return this;
        }

        public Builder points(List<String> points) {
            this.points = Collections.unmodifiableList(points);
            return this;
        }

        public GameHistory build() {
            return new GameHistory(this);
        }
    }

}
