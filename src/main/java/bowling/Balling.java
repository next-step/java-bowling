package bowling;

import java.util.List;

public class Balling {

    private List<User> users;

    private List<BallingRound> rounds;

    public Balling() {
    }

    public BallingRound play(int numberOfPins) {
        return new BallingRound(1);
    }
}
