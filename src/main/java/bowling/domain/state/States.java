package bowling.domain.state;

import java.util.Arrays;
import java.util.LinkedList;

public final class States {

    private final LinkedList<State> states;

    private States() {
        this.states = new LinkedList<>(Arrays.asList(Ready.initialize()));
    }

    public static final States initialize() {
        return new States();
    }


}
