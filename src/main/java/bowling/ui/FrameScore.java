package bowling.ui;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class FrameScore {
    private final Queue<Integer> frameScore;

    public FrameScore() {
        frameScore = new LinkedList<>();
    }

    public void addScore(int score) {
        frameScore.add(score);
    }

    @Override
    public String toString() {
        Queue<Integer> frameScore = new LinkedList<>(this.frameScore);
        StringJoiner stringJoiner = new StringJoiner("|");
        if (frameScore.isEmpty()) {
            return "";
        }

        while (!frameScore.isEmpty()) {
            z(frameScore, stringJoiner);
        }
        return stringJoiner.toString();
    }

    private void z(Queue<Integer> frameScore, StringJoiner stringJoiner) {
        if (frameScore.peek() == 10) {
            stringJoiner.add("X");
            frameScore.poll();
            return;
        }
        if (frameScore.peek() == 0) {
            stringJoiner.add("-");
            frameScore.poll();
            return;
        }
        if (frameScore.size() == 1) {
            stringJoiner.add(String.valueOf(frameScore.poll()));
            return;
        }
        int score = 10;
        int v1 = frameScore.poll();

        score = score - v1;
        stringJoiner.add(String.valueOf(v1));

        int v2 = frameScore.poll();

        score = score - v2;

        if (score == 0) {
            stringJoiner.add("/");
            return;
        }
        if (v2 == 0) {
            stringJoiner.add("-");
            return;
        }
        stringJoiner.add(String.valueOf(v2));
    }
}
