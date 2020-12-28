package step2.domain.state;

import step2.domain.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Strike implements State {

    private final static String SYMBOL = "X";
//    private final static String DELIMITER = "|";
//
//    private final List<Pitch> pitches;
//
//    public Strike(List<Pitch> pitches) {
//        this.pitches = pitches;
//    }

    @Override
    public String toString() {
        return SYMBOL;
    }

}
