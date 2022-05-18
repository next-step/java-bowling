package bowling.domain.frameresult;

import java.util.Optional;

public class Bonus {

    private Integer spareBonus;
    private Integer strikeBonus;

    public Bonus() {
    }

    public void setSpareBonus(int spareBonus) {
        this.spareBonus = spareBonus;
    }

    public void setStrikeBonus(int strikeBonus) {
        this.strikeBonus = strikeBonus;
    }

    public Optional<Integer> getSpareBonus() {
        return Optional.ofNullable(spareBonus);
    }

    public Optional<Integer> getStrikeBonus() {
        return Optional.ofNullable(strikeBonus);
    }
}
