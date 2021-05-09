package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BonusScoreCountTest {

    @DisplayName("BonusScoreCount 인스턴스 생성 여부 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 생성(int remain) {

        BonusScoreCount bonusScoreCount = BonusScoreCount.from(remain);

        assertThat(bonusScoreCount).isNotNull();
    }
}