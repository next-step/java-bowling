package bowling.domain;

import bowling.state.State;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private List<Integer> bowls;
    private State state;

    public static NormalFrame create() {
        return null;
    }

    public static NormalFrame from(Frame previous) {
        return null;
    }

    @Override
    public BowlResult bowl(int value) {
        State state = State.bowl(value);
        return null;
    }

    @Override
    public boolean canBowl() {
        return false;
    }

    @Override
    public String getResult() {
//        List<BowlResult> bowlResults = new ArrayList<>();
//
//        int previous = bowls.get(0);
//        BowlResult firstResult = BowlResult.of(previous);
//        for (int i = 1; i < bowls.size() - 1; i++) {
//            int now = bowls.get(i + 1);
//            BowlResult bowlResult = BowlResult.of(previous, now);
//            bowlResults.add(bowlResult);
//            previous = bowls.get(i);
//        }
//
//        return "bowlResults";
        return state.expression();
    }

}
