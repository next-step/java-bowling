package bowling.domain.state;

import bowling.domain.frame.Frame;

import java.util.List;

public interface FrameState {

    FrameState GUTTER = new Gutter();
    FrameState MISS = new Miss();
    FrameState SPARE = new Spare();
    FrameState STRIKE = new Strike();

    List<FrameState> ALL_STATES = List.of(GUTTER, MISS, SPARE, STRIKE);

    static FrameState from(List<Integer> hits) {
        return ALL_STATES.stream()
                .filter(state -> state.identify(hits))
                .findFirst()
                .orElse(null);
    }

    boolean identify(List<Integer> hits);

    default boolean hasScore(Frame currentFrame) {
        if (currentFrame.isOnGoing()) {
            return false;
        }

        int count = 0;
        Frame frame = currentFrame.getNextFrame();
        while (hasMoreHits(count, frame)) {
            count += frame.countHits();
            frame = frame.getNextFrame();
        }
        return count >= getNumberOfBonus();
    }

    private boolean hasMoreHits(int count, Frame frame) {
        return frame != null && frame.countHits() > 0 && count < getNumberOfBonus();
    }

    default int calculateScore(Frame currentFrame) {
        if (!hasScore(currentFrame)) {
            throw new IllegalStateException("점수를 계산할 수 없는 상태입니다.");
        }

        return sumHits(currentFrame);
    }

    private int sumHits(Frame currentFrame) {
        int count = 0;
        int index = 0;
        int sum = 0;
        Frame frame = currentFrame;
        while (count < currentFrame.countHits() + getNumberOfBonus()) {
            sum += frame.getHit(index++);
            if (index >= frame.countHits()) {
                frame = frame.getNextFrame();
                index = 0;
            }
            count++;
        }
        return sum;
    }

    default int getNumberOfBonus() {
        return 0;
    }

}
