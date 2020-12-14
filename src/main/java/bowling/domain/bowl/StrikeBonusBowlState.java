package bowling.domain.bowl;

class StrikeBonusBowlState extends BonusBowlState {
    StrikeBonusBowlState(BowlState state) {
        super(state, 2);
    }
}
