package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class States {
    private static final int FIRST_Pitch_STATE = 0;
    private static final int SECOND_Pitch_STATE = 1;
    private static final int BONUS_Pitch_STATE = 2;

    private List<State> states;

    public States() {
        states = new ArrayList<>();
    }

    public States(List<State> states) {
        this.states = states;
    }

    public static States of(List<State> states) {
        return new States(states);
    }

    public void add(State state) {
        states.add(state);
    }

    public State getFirstPitch() {
        return states.get(FIRST_Pitch_STATE);
    }

    public State getSecondPitch() {
        return states.get(SECOND_Pitch_STATE);
    }

    public State getBonusPitch() {
        return states.get(BONUS_Pitch_STATE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        States states1 = (States) o;
        return Objects.equals(states, states1.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
