package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int NOT_SCORE_STATE = -1;
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        frames.add(frame);
    }

    public Frame getFrame(int i) {
        if(i > this.frames.size()) {
            throw new IllegalArgumentException("프레임 사이즈보다 큰 숫자를 입력 했습니다.");
        }
        return frames.get(i - 1);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public List<Integer> calculateTotalScore(int i) {
        List<Integer> result = new ArrayList<>();
        int total = 0;
        for(int j = 1; j<=i; j++) {
            total = addTotal(result, total, j);
        }
        return result;
    }

    private int addTotal(List<Integer> result, int total, int j) {
        if (!checkNotScoreState(result, j)) {
            total += getFrame(j).score();
            result.add(total);
        }
        return total;
    }

    private boolean checkNotScoreState(List<Integer> result, int j) {
        if(getFrame(j).score()== NOT_SCORE_STATE) {
            result.add(NOT_SCORE_STATE);
            return true;
        }
        return false;
    }

}
