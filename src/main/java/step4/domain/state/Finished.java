package step4.domain.state;

abstract class Finished implements State {

    @Override
    public State throwBowl(int fallenPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
