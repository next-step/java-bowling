package bowling.domain.state;

abstract class NotFinished implements State {

    @Override
    public boolean isEndedState() {
        return false;
    }
}
