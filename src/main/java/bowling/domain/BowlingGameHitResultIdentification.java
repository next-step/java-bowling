package bowling.domain;

import java.util.List;

@FunctionalInterface
public interface BowlingGameHitResultIdentification {

    boolean identify(List<Integer> hits);

}
