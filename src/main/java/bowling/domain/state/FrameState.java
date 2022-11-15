package bowling.domain.state;

import java.util.List;

public interface FrameState {

    FrameState MISS = new Miss();
    FrameState SPARE = new Spare();
    FrameState STRIKE = new Strike();

    List<FrameState> ALL_STATES = List.of(MISS, SPARE, STRIKE);

    static FrameState from(List<Integer> hits) {
        return ALL_STATES.stream()
                .filter(state -> state.identify(hits))
                .findFirst()
                .orElse(null);
    }

    boolean identify(List<Integer> hits);

    default int getNumberOfBonus() {
        return 0;
    }

}
