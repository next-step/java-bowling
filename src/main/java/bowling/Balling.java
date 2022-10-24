package bowling;

import java.util.List;

public class Balling {

    private List<User> users;

    private BallingRound currentRound;

    public Balling() {
        this.currentRound = new BallingRound(1);
    }

    public BallingRound play(int numberOfPins) {
       return currentRound.add(numberOfPins);
    }
}
