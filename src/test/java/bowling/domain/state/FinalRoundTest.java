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

    @DisplayName("FinalRound 인스턴스의 보너스 없을 때 종료 기능 테스트")
    @Test
    void 종료_보너스_없을때() {
        // given
        boolean bonus = false;

        // when
        FinalRound finalRound = FinalRound.initialize();
        finalRound = finalRound.next();
        finalRound = finalRound.next();

        // then
        assertThat(finalRound.isFinish(bonus)).isTrue();
    }

    @DisplayName("FinalRound 인스턴스의 보너스 았을 때 종료 기능 테스트")
    @Test
    void 종료_보너스_있을때() {
        // given
        boolean bonus = true;

        // when
        FinalRound finalRound = FinalRound.initialize();
        FinalRound firstRound = finalRound.next();
        FinalRound secondRound = firstRound.next();
        FinalRound thirdRound = secondRound.next();

        // then
        assertAll(
                () -> assertThat(secondRound.isFinish(bonus)).isFalse(),
                () -> assertThat(thirdRound.isFinish(bonus)).isTrue()
        );

    }
}