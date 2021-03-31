package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements PlayableFrame {

    private final int number;

    private final List<Integer> pintCounts = new ArrayList<>();

    public FinalFrame(int number) {
        this.number = number;
    }

    @Override
    public void addPintCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        pintCounts.add(pinCount);
    }

    @Override
    public List<Integer> pinCounts() {
        return pintCounts;
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public boolean isDone() {
        int totalPinCounts = pintCounts.stream()
                .reduce(0, Integer::sum);

        if (pintCounts.size() == 3) {
            return true;
        }
        if (pintCounts.size() == 2 && totalPinCounts < 10) {
            return true;
        }
        return false;
    }
}
