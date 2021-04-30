package bowling.domain.status;

public class Default extends Continue {
    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }
}
