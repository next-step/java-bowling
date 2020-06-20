package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {

    private static final int MAX_ROUND = 2;

    private final int position;

    private final List<Integer> downPins = new ArrayList<>();

    private NormalFrame(int position) {
        this.position = position;
    }

    static NormalFrame first() {
        return new NormalFrame(0);
    }

    NormalFrame next() {
        return new NormalFrame(this.position + 1);
    }

    @Override
    public void play(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("이미 종료된 frame입니다.");
        }

        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        if (sumDownPin() == 10) {
            return false;
        }

        return true;
    }


    @Override
    public FrameBowlStates getBowlStates() {
        if (isStrike()) {
            return new FrameBowlStates(Arrays.asList(new FrameBowlState(10, ScoreType.STRIKE)));
        }

        if (isSpare()) {
            return new FrameBowlStates(Arrays.asList(
                new FrameBowlState(this.downPins.get(0), ScoreType.MISS),
                new FrameBowlState(this.downPins.get(1), ScoreType.SPARE)
            ));
        }

        return new FrameBowlStates(
            this.downPins.stream()
                .map(downPin -> new FrameBowlState(downPin,
                    downPin == 0 ? ScoreType.GUTTER : ScoreType.MISS))
                .collect(Collectors.toList())
        );
    }

    private boolean isSpare() {
        if (this.downPins.size() == 2 && sumDownPin() == 10) {
            return true;
        }

        return false;
    }

    private boolean isStrike() {
        if (this.downPins.size() == 1 && this.downPins.get(0) == 10) {
            return true;
        }

        return false;
    }

    private int sumDownPin() {
        return downPins.stream().reduce(0, Integer::sum);
    }
}
