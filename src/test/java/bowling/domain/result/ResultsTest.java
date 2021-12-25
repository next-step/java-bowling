package bowling.domain.result;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultsTest {

    private Results results;

    @BeforeEach
    void init() {
        results = new Results();
    }

    @Test
    @DisplayName("파라미터 보다 작으면, true를 반환한다.")
    void sizeLessThanTest() {
        assertThat(results.sizeLessThan(-1)).isFalse();
        assertThat(results.sizeLessThan(0)).isFalse();
        assertThat(results.sizeLessThan(1)).isTrue();
    }

    @Test
    @DisplayName("파라미터 보다 작거나 같으면, true를 반환한다.")
    void sizeLessOrEqualThanTest() {
        assertThat(results.sizeLessOrEqualThan(-1)).isFalse();
        assertThat(results.sizeLessOrEqualThan(0)).isTrue();
        assertThat(results.sizeLessOrEqualThan(1)).isTrue();
    }
}