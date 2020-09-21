package bowling.domain.bowl;

import java.util.LinkedList;

public class FinalBowl {

    private final LinkedList<Bowl> bowls = new LinkedList<>();

    public FinalBowl() {
        add();
    }

    public BowlResult bowl(int numberOfPins) {
        return bowls.getLast().bowl(numberOfPins);
    }

    public void add() {
        bowls.add(new Bowl());
    }

}