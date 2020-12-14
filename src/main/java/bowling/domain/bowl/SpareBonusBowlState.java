package bowling.domain.bowl;

class SpareBonusBowlState extends BonusBowlState {
    SpareBonusBowlState(BowlState state) {
        super(state, 1);
    }
}
