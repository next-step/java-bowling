package bowling.domain.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RecordsTest {

    @Test
    @DisplayName("저장된 기록이 스트라이크인지 확인한다.")
    void existsStrikeInRecords() {
        Records records = new Records();
        records.add(PitchResult.wrap(10));

        assertAll(
            () -> assertThat(records.isStrike()).isTrue(),
            () -> assertThat(records.isSpare()).isFalse(),
            () -> assertThat(records.isMissed()).isFalse()
        );
    }

    @Test
    @DisplayName("두 번의 시도로 10 개의 핀이 쓰러졌다면 스페어 처리가 된 것이다.")
    void checkIfSpare() {
        Records records = new Records();
        records.add(PitchResult.wrap(8));
        records.add(PitchResult.wrap(2));

        assertAll(
            () -> assertThat(records.isStrike()).isFalse(),
            () -> assertThat(records.isSpare()).isTrue(),
            () -> assertThat(records.isMissed()).isFalse()
        );
    }

    @Test
    @DisplayName("두 번의 시도를 다 쓰고도 핀이 남았다면 미스 처리가 된 것이다.")
    void checkIfMissed() {
        Records records = new Records();
        records.add(PitchResult.wrap(8));
        records.add(PitchResult.wrap(1));

        assertAll(
            () -> assertThat(records.isSpare()).isFalse(),
            () -> assertThat(records.isMissed()).isTrue()
        );
    }

}
