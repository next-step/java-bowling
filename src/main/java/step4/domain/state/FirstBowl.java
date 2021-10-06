package step4.domain.state;

public class FirstBowl implements State {
    private int falledPins;

    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
    }

    public State throwBowl(int falledPins) {
        if (this.falledPins + falledPins == 10) {
            return new Spair();
        }
        return new LastBowl(this.falledPins + falledPins);
    }


}
