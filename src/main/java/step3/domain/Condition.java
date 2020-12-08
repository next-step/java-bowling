package step3.domain;

import step3.domain.dto.PointDTO;

@FunctionalInterface
public interface Condition {
    boolean filter(PointDTO dto);
}
