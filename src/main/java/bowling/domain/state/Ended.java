package bowling.domain.state;

abstract class Ended implements State {

    @Override
    public boolean isEndedState() {
        return true;
    }
}
