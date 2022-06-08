package bowling.domain.state;

abstract class Ongoing implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean hasBonusChance() {
        return false;
    }
}
