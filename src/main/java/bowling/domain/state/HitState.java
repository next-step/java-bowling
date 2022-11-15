package bowling.domain.state;

import java.util.List;

public interface HitState {

    HitState GUTTER = new Gutter();
    HitState MISS = new Miss();
    HitState SPARE = new Spare();
    HitState STRIKE = new Strike();

    List<HitState> ALL_STATES = List.of(GUTTER, MISS, SPARE, STRIKE);

    static HitState from(int hit, Integer previousHit) {
        return ALL_STATES.stream()
                .filter(state -> state.identify(hit, previousHit))
                .findFirst()
                .orElse(null);
    }

    boolean identify(int hit, Integer previousHit);

    default int getNumberOfBonus() {
        return 0;
    }

}
