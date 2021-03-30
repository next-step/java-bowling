package bowling.domain;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface Frame {

    int number();

    void addPintCount(int pinCount);

    List<Integer> pinCounts();
}
