package camp.nextstep.edu.rebellion.bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameSymbolTest {
    @DisplayName("입력한 점수에 맞게 Symbol이 잘 반환 되는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "10:X",
            "0:-",
            "9:9"
    }, delimiter = ':')
    public void frameSymbolTest(int input, String expected) {
        // when & then
        assertThat(FrameSymbol.of(input).equals(expected)).isTrue();
    }
}