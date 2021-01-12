package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private List<Chance> chances = new ArrayList<>();

    public Pins() {
        rollTheBall();
    }

    private void rollTheBall() {
        Chance chance = new Chance();
        this.chances.add(chance);
    }

    public void rollTheBallNext() {
        Chance lastChance = chances.get(chances.size() - 1);
        chances.add(Chance.nextRollTheBall(lastChance));
    }

    public void getOneMoreChance() {
        rollTheBall();
    }

    public int getTotalScore() {
        int totalSocre = 0;

        for (Chance chance : this.chances) {
            totalSocre = totalSocre + chance.getCountOfPin();
        }
        return totalSocre;
    }

    public int getCountOfPin(final int chanceIndex) {
        return chances.get(chanceIndex).getCountOfPin();
    }

    public int plusBeforePinAndNowPin(final int chanceIndex) {
        return chances.get(chanceIndex).getCountOfPin() + chances.get(chanceIndex - 1).getCountOfPin();
    }

    public List<Chance> getChances() {
        return chances;
    }
}
