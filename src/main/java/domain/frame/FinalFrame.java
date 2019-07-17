package domain.frame;

import domain.Pins;
import domain.state.Miss;
import domain.state.StandBy;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int INDEX_OF_FINAL_FRAME = 10;
    private static final int MAXIMUM_BOWL_ORDER = 3;

    private int bowlOrder;
    private List<State> states = new ArrayList<>();

    private FinalFrame() {
        this.bowlOrder = 0;
        this.states.add(new StandBy());
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public Frame fillFrame(Pins fallenPins) {
        if (isGameOver()) {
            throw new RuntimeException("게임이 끝났습니다."); //TODO: 예외 만들기
        }
        bowlOrder++;

        if (currentState().isClosed()) {
            this.states.add(new StandBy().update(fallenPins));
            return this;
        }
        State newState = currentState().update(fallenPins);
        states.set(getLastBowlOrder(), newState);

        return this;
    }

    boolean isGameOver() {
        return bowlOrder == MAXIMUM_BOWL_ORDER || isStateMiss();
    }

    private boolean isStateMiss() {
        return states.get(getLastBowlOrder()) instanceof Miss;
    }

    private State currentState() {
        return states.get(getLastBowlOrder());
    }

    private int getLastBowlOrder() {
        return states.size() - 1;
    }

    FrameIndex getIndex() {
        return FrameIndex.from(INDEX_OF_FINAL_FRAME);
    }
}
