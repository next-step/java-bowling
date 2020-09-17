package bowling.domain;

public interface NormalBowlFormatter {

    boolean isSupport(NormalBowl normalBowl);
    String format(NormalBowl normalBowl);

}
