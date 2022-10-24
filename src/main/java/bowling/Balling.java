package bowling;

import java.util.List;

public class Balling {

    private List<User> users;

    private BallingRound currentRound;

    public Balling() {
        this.currentRound = new BallingRound(1);
    }

    public BallingRound play(int numberOfPins) {
        BallingRound ballingRound = currentRound.addKnockDownPins(numberOfPins);
        this.currentRound = ballingRound;
        return ballingRound;
    }
}
