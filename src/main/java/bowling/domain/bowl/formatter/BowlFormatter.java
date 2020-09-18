package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

@FunctionalInterface
public interface BowlFormatter {

    String format(Bowl bowl);

}
