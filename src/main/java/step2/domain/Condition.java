package step2.domain;

import step2.domain.dto.PointDTO;

@FunctionalInterface
public interface Condition {
    boolean filter(PointDTO dto);
}
