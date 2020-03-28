package bowling.domain.state;

public class Strike implements State {

    private int fistFallenPins;
    private int secondFallenPins;
    private String display;
    private boolean finish;

    public Strike(int fistFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.display = "X";
        this.finish = false;
    }

    public Strike(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.display = String.format("X|%s", convert(secondFallenPins));
        this.finish = true;
    }

    public Strike() {
        this.display = "X";
        this.finish = true;
    }

    private String convert(int number) {
        if (number == 10) {
            return "X";
        }
        return String.valueOf(number);
    }

    @Override
    public State bowl(int pins) {
        return new Strike(this.fistFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return this.finish;
    }

    @Override
    public String display() {
        return this.display;
    }
}
