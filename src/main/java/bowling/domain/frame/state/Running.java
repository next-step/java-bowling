package bowling.domain.frame.state;

abstract class Running implements State {
    @Override
    public final boolean isFinish() {
        return false;
    }
}
