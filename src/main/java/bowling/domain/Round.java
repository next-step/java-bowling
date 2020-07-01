package bowling.domain;

public class Round {
    public static final Round FIRST_ROUND = new Round(1);
    public static final Round SECOND_ROUND = new Round(2);
    
    private final int value;

    private Round(final int value) {
        this.value = value;
    }
}
