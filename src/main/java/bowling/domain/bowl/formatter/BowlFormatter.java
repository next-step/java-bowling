package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

@FunctionalInterface
public interface BowlFormatter {

    String format(BowlResult bowlResult);

}
