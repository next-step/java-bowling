package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frame2 {

    private final List<Bowling> values = new ArrayList<>();
    private final FrameStrategy strategy;

    public Frame2(FrameStrategy strategy) {
        this.strategy = strategy;
    }

    public static Frame2 createNormal() {
        return new Frame2(new NormalStrategy());
    }

    public static Frame2 createFinal() {
        return new Frame2(new FinalStrategy());
    }

    public void bowling(int pinCount) {
        values.add(Bowling.from(this, PinCount.of(pinCount)));
    }

    public void bowling(PinCount pinCount) {
        values.add(Bowling.from(this, pinCount));
    }

    public boolean isEnd() {
        return strategy.isFrameEnd(getRound(), beforeResult());
    }

    public int getRound() {
        return values.size();
    }

    public PinCount beforeCount() {
        return getBeforeBowling().getPinCount();
    }

    private Result beforeResult() {
        if (isFirstRound()) {
            return Result.NONE;
        }

        return getBeforeBowling().getResult();
    }

    private Bowling getBeforeBowling() {
        return values.get(values.size() - 1);
    }

    private boolean isFirstRound() {
        return getRound() == Result.FIRST_ROUND;
    }

    public Result getResult(int round) {
        return getBowling(round).getResult();
    }

    public int getCount(int round) {
        return getBowling(round).getCount();
    }


    private Bowling getBowling(int round) {
        return Optional.ofNullable(values.get(round))
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인덱스 입니다. " + round));
    }

    //=============================================================

    @Override
    public String toString() {
        return "Frame2{" +
                "values=" + values +
                ", strategy=" + strategy +
                '}';
    }
}
