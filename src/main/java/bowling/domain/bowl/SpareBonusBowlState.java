package bowling.domain.bowl;

public class SpareBonusBowlState extends BonusBowlState {
    SpareBonusBowlState(BowlState state) {
        super(state, 1);
    }
}
