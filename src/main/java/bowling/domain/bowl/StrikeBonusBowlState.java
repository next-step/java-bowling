package bowling.domain.bowl;

public class StrikeBonusBowlState extends BonusBowlState {
    StrikeBonusBowlState(BowlState state) {
        super(state, 2);
    }
}
