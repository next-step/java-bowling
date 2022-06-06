package refactor;

import java.util.Objects;

public class Frame {
    private Score score;
    private Frame next;

//    public Frame() {
//        this.score = new Score();
//        this.next = null;
//    }

    public Frame(Frame next) {
        this.score = new Score();
        this.next = next;
    }

    public Frame(Score score, Frame next) {
        this.score = score;
        this.next = next;
    }

    public static Frame last() {
        return new Frame(new Score(0, 3), null);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "score=" + score +
                ", next=" + next +
                "}\n";
    }

    public Frame next() {
        return this.next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(score, frame.score) && Objects.equals(next, frame.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, next);
    }

    public void pitch() {
        this.score = score.pitch();
    }

    //    public int score() {
//        if (!score.playing()) {
//            return score.score();
//        }
//        return this.next.calculateBonus(score);
//    }

//    private Score createScore() {
//    }

//    private int calculateBonus(Score previousScore) {
//
//    }
}
