package step4.domain.state;

public class FirstBowl extends State {
    private final int falledPins;

    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
    }

    public State throwBowl(int falledPins) {
        if (this.falledPins + falledPins == 10) {
            return new Spare();
        }
        return new LastBowl();
    }
}
