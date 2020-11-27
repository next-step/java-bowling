package step2.domain;

import java.util.List;

public class GameHistory {
    private final int currentFrameNo;
    private final int pitchesPoint;
    private final Player player;
    private final List<String> marks;
    private final List<String> points;

    public GameHistory(Builder builder) {
        this.currentFrameNo = builder.currentFrameNo;
        this.pitchesPoint = builder.pitchesPoint;
        this.marks = builder.marks;
        this.player = builder.player;
        this.points = builder.points;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public int getCurrentFrameNo() {
        return currentFrameNo;
    }

    public int getPitchesPoint() {
        return pitchesPoint;
    }

    public Player getPlayer() {
        return player;
    }

    public List<String> getMarks() {
        return marks;
    }
    public List<String> getPoints() {
        return points;
    }


    public static class Builder {
        private int currentFrameNo;
        private int pitchesPoint;
        private Player player;
        private List<String> marks;
        private List<String> points;

        public Builder() { }

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder currentFrameNo(int currentFrameNo) {
            this.currentFrameNo = currentFrameNo;
            return this;
        }

        public Builder pitchesPoint(int pitchesPoint) {
            this.pitchesPoint = pitchesPoint;
            return this;
        }

        public Builder marks(List<String> marks) {
            this.marks = marks;
            return this;
        }
        public Builder points(List<String> points) {
            this.points = points;
            return this;
        }

        public GameHistory build() {
            return new GameHistory(this);
        }
    }


}
