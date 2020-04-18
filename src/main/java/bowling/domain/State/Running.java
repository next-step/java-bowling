package bowling.domain.State;

abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }
}
