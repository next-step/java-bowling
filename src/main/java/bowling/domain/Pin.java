package bowling.domain;

public class Pin {

    private boolean knockedDown = false;

    private final int number;

    public Pin(int number) {
        this.number = number;
    }

    public void knockDown(){
        this.knockedDown = true;
    }

    public boolean isKnockedDown() {
        return knockedDown;
    }

    public boolean isStanding() {
        return !knockedDown;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "knockedDown=" + knockedDown +
                ", number=" + number +
                '}';
    }
}
