package bowling.domain.frame;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NormalPins implements Pins{
    private static final int PIN_COUNT = 10;
    private static final int MAX_ROUND = 2;

    private final List<Integer> downPins = new ArrayList<>();

    @Override
    public void down(int downPin) {
        validate(downPin);
        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        if (sumOfDownPin() == 10) {
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
                new FrameBowlState(this.downPins.get(0), ScoreType.NUMS),
                new FrameBowlState(this.downPins.get(1), ScoreType.SPARE)
            ));
        }

        return new FrameBowlStates(
            this.downPins.stream()
                .map(downPin -> new FrameBowlState(downPin,
                    downPin == 0 ? ScoreType.GUTTER : ScoreType.NUMS))
                .collect(Collectors.toList())
        );
    }

    private void validate(int downPin) {
        if(!hasTurn()){
            throw new IllegalStateException("max down limit");
        }

        if(downPin < 0){
            throw new IllegalArgumentException("invalid downPin");
        }

        if(sumOfDownPin() + downPin > PIN_COUNT){
            throw new IllegalArgumentException("invalid downPin");
        }
    }

    private int sumOfDownPin(){
        return this.downPins.stream().reduce(0, Integer::sum);
    }

    private boolean isSpare() {
        if (this.downPins.size() == 2 && sumOfDownPin() == 10) {
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
}
