package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

public interface NormalBowlFormatter {

    boolean isSupport(NormalBowl normalBowl);
    String format(NormalBowl normalBowl);

}
