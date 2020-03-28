package bowling.domain.state;

public class ReadyFinal implements State {

    @Override
    public State bowl(int pins) {
        if (pins == 10) {
            return new Strike(pins);
        }
        return new NextReady(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return null;
    }
}
