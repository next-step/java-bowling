package bowling.domain.state;

public class Ready implements State{

    @Override
    public State bowl(int pins) {
        if (pins == 10) {
            return new Strike();
        }
        return new NextReady(pins);
    }
}
