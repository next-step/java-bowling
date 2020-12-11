package step4.domain;

import step4.domain.dto.PointDTO;

@FunctionalInterface
public interface Condition {
    boolean filter(PointDTO dto);
}
