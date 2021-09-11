package bowling;


import java.util.List;

public interface Pins {
    Pins first(int countOfDownPin);

    Pins next(int countOf);

    boolean isEnd();

    ScoreRule scoreRule();

    List<Pin> pins();

    Score score();

    BonusChance bonusChance();
}
