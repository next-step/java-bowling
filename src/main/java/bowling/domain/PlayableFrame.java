package bowling.domain;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PlayableFrame {

    int number();

    void addPintCount(int pinCount);

    List<Integer> pinCounts();

    boolean isDone();
}
