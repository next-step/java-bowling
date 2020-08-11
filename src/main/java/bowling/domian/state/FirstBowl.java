package bowling.domian.state;

public class FirstBowl implements State {
    public FirstBowl(Pins bowl) {

    }

    public State bowl(int falledPinsCount) {
        return new FirstBowl(Pins.bowl(falledPinsCount));
    }

    public boolean isFinished() {
        return true;
    }
}
