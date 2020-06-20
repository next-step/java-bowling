package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{

    private final List<Integer> downPins = new ArrayList<>();

    private final Frame normalFrame = NormalFrame.first();


    @Override
    public void play(int downPin) {
        if(this.normalFrame.hasTurn()){
            this.normalFrame.play(downPin);
            return;
        }

        if (!hasTurn()) {
            throw new IllegalStateException("can not play");
        }


        downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (normalFrame.hasTurn()) {
            return true;
        }


        if(this.downPins.size() < getBonusCount()){
            return true;
        }

        return false;
    }

    @Override
    public List<FrameBowlState> getBowlStates() {
        List<FrameBowlState> bowlStates = new ArrayList<>(normalFrame.getBowlStates());

        bowlStates.addAll(this.downPins.stream().map(
            integer -> new FrameBowlState(integer, integer == 10 ? ScoreType.STRIKE : ScoreType.MISS))
            .collect(Collectors.toList())
        );

        return bowlStates;
    }


    private int getBonusCount() {
        List<FrameBowlState> states = normalFrame.getBowlStates();

        boolean hasStrike = states.stream()
            .filter(frameBowlState -> frameBowlState.getScoreType() == ScoreType.STRIKE)
            .findFirst()
            .isPresent();

        if (hasStrike) {
            return 2;
        }

        boolean hasSpare = states.stream()
            .filter(frameBowlState -> frameBowlState.getScoreType() == ScoreType.SPARE)
            .findFirst()
            .isPresent();

        if (hasSpare) {
            return 1;
        }

        return 0;
    }

}
