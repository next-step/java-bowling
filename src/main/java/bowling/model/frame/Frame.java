package bowling.model.frame;

import bowling.model.Result;
import bowling.model.frame.dto.FrameDto;

import java.util.List;

public abstract class Frame {
    public static final int MAX_FRAME_INDEX = 10;

    protected final int index;
    protected final List<Result> results;

    protected Frame(int index, List<Result> results) {
        this.index = index;
        this.results = results;
    }

    public abstract Frame next();

    public void addResult(int count) {
        if (isEnded()) {
            throw new IllegalStateException("이미 끝난 프레임입니다.");
        }
        if (getTotalPinCount() + count > Result.MAX_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("넘어진 핀의 수가 %d를 넘어갈 수 없습니다.", Result.MAX_PIN_COUNT));
        }
        if (willBeSpare(count)) {
            results.add(Result.spare(count));
            return;
        }
        results.add(Result.of(count));
    }

    public int getIndex() {
        return index;
    }

    public boolean isEnded() {
        return hasStrike() || results.size() == 2;
    }

    public boolean hasSpare() {
        return results.stream()
                .anyMatch(Result::isSpare);
    }

    public boolean hasStrike() {
        return results.stream()
                .anyMatch(Result.STRIKE::equals);
    }

    protected int getTotalPinCount() {
        return results.stream()
                .map(Result::get)
                .reduce(0, (acc, cur) -> acc += cur);
    }

    public FrameDto getDto() {
        return new FrameDto(index, results);
    }

    private boolean willBeSpare(int pinCount) {
        int totalPinCount = getTotalPinCount();
        return totalPinCount != 0 && totalPinCount + pinCount == Result.MAX_PIN_COUNT;
    }
}
