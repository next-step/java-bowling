package bowling.domain.state;

import bowling.domain.frame.BowlingGameFrame;

import java.util.List;

public interface BowlingGameHitState {

    BowlingGameHitState GUTTER = new Gutter();
    BowlingGameHitState MISS = new Miss();
    BowlingGameHitState SPARE = new Spare();
    BowlingGameHitState STRIKE = new Strike();

    List<BowlingGameHitState> ALL_STATES = List.of(GUTTER, MISS, SPARE, STRIKE);

    static BowlingGameHitState from(List<Integer> hits) {
        return ALL_STATES.stream()
                .filter(state -> state.identify(hits))
                .findFirst()
                .orElse(null);
    }

    boolean identify(List<Integer> hits);

    default boolean hasScore(BowlingGameFrame currentFrame) {
        if (currentFrame.isOnGoing()) {
            return false;
        }

        int count = 0;
        BowlingGameFrame frame = currentFrame.getNextFrame();
        while (hasMoreHits(count, frame)) {
            count += frame.countHits();
            frame = frame.getNextFrame();
        }
        return count >= getNumberOfBonus();
    }

    private boolean hasMoreHits(int count, BowlingGameFrame frame) {
        return frame != null && frame.countHits() > 0 && count < getNumberOfBonus();
    }

    default int calculateScore(BowlingGameFrame currentFrame) {
        if (!hasScore(currentFrame)) {
            throw new IllegalStateException("점수를 계산할 수 없는 상태입니다.");
        }

        return sumHits(currentFrame);
    }

    private int sumHits(BowlingGameFrame currentFrame) {
        int count = 0;
        int index = 0;
        int sum = 0;
        BowlingGameFrame frame = currentFrame;
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
