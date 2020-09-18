package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

public interface NormalBowlFormatter {

    boolean isSupport(Bowl bowl);
    String format(Bowl bowl);

}
