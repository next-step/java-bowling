package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;

public class NormalFrame implements Frame {
    private int round;
    private State state;
    private Frame nextFrame;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();

    }

    public Frame createFrame(int round) {
        if (round + 1 < 10) {
            this.nextFrame = new NormalFrame(round + 1);
        }
        return this.nextFrame;
    }

    public void throwBowl(int falledPins) {
        state = state.throwBowl(falledPins);
    }

    public State state() {
        return state;
    }

    public String getScore() {
        Score score = state.score();

        if (score.canCalculate()) {
            return score.getScore();
        }
        System.out.println(nextFrame == null);
        if (nextFrame == null) {
            return "";
        }
        return nextFrame.calculateScoreFromNextFrame(score);
    }

    public String calculateScoreFromNextFrame(Score beforeScore) {
         beforeScore = state.calculateScore(beforeScore);
         if (beforeScore.canCalculate()) {
             return beforeScore.getScore();
         }
        System.out.println(nextFrame == null);
        if (nextFrame == null) {
            return "";
        }
        return nextFrame.calculateScoreFromNextFrame(beforeScore);
    }
}
