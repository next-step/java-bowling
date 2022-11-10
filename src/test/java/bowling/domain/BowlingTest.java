package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {
    
    @Test
    void 생성_성공() {
        Bowling bowling = Bowling.of(10);
        assertThat(bowling.getCount()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1","11"})
    void 생성_실패(int count) {
        assertThatIllegalArgumentException().isThrownBy(() -> Bowling.of(count));
    }
}
