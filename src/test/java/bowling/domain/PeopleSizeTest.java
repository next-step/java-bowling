package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PeopleSizeTest {

    @DisplayName("참가자는 1 이상이어야 합니다.")
    @Test
    void validate_size() {
        assertAll(
                () -> assertThatThrownBy(() -> new PeopleSize(0)).isInstanceOf(IllegalArgumentException.class),
                () -> assertDoesNotThrow(() -> new PeopleSize(1))
        );
    }
}
