package camp.nextstep.edu.rebellion.bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {
    private final int MAX_FRAME_SCORES = 10;
    private final int MAX_FRAME_ROUNDS = 10;
    private final List<Integer> scores;
    private final List<Frame> frames;
    private int round = 0;

    private BowlingGame() {
        scores = new ArrayList<>();
        for (int i = 0; i < MAX_FRAME_ROUNDS; i++) {
            scores.add(0);
        }
        frames = new ArrayList<>();
        for (int i = 0; i < MAX_FRAME_ROUNDS; i++) {
            frames.add(Frame.ready());
        }
    }

    public static BowlingGame start() {
        return new BowlingGame();
    }

    public void record(int score) {
        int cur = scores.get(round);
        scores.remove(round);
        scores.add(round, cur + score);

        // Case 1. cur + score 가 10에 도달했을 경우 다음 round 로 진행
        // Case 2. 두번 째 투구일 경우 score 에 상관없이 다음 round 로 진행
        if (MAX_FRAME_SCORES == (cur + score)) {
            // has twice
            round++;
        }
    }

    public int currentRound() {
        return this.round;
    }

    public boolean hasNext() {
        return MAX_FRAME_ROUNDS > round;
    }

    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores);
    }
}
