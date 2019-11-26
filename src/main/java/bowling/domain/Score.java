package bowling.domain;

import java.util.List;

public interface Score {

    Score ofNext(int pin);

    boolean isFinished();

    List<Symbol> getSymbols();
}
