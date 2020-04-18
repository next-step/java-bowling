package bowling.domain.state;

abstract class Finished implements State {

    @Override
    public boolean isEndedState() {
        return true;
    }
}
