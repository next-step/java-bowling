package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalRoundTest {

    @DisplayName("FinalRound 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        FinalRound finalRound = FinalRound.initialize();

        assertThat(finalRound).isNotNull();
    }
}