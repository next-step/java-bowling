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
        records.save(PitchResult.wrap(10));

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
        records.save(PitchResult.wrap(8));
        records.save(PitchResult.wrap(2));

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
        records.save(PitchResult.wrap(8));
        records.save(PitchResult.wrap(1));

        assertAll(
            () -> assertThat(records.isSpare()).isFalse(),
            () -> assertThat(records.isMissed()).isTrue()
        );
    }

    @Test
    @DisplayName("일반 프레임에서 스트라이크를 표현한다.")
    void strikeInNormalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(10));

        assertThat(records.export().getRecords()).containsExactly("X");
    }

    @Test
    @DisplayName("일반 프레임에서 스페어를 표현한다.")
    void spareInNormalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(2));
        records.save(PitchResult.wrap(8));

        assertThat(records.export().getRecords()).contains("2", "/");
    }

    @Test
    @DisplayName("일반 프레임에서 미스 상태를 표현한다.")
    void missInNormalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(2));
        records.save(PitchResult.wrap(7));

        assertThat(records.export().getRecords()).containsExactly("2", "7");
    }

    @Test
    @DisplayName("0점은 '-' 로 표기한다.")
    void gutterInNormalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(8));
        records.save(PitchResult.wrap(0));

        assertThat(records.export().getRecords()).containsExactly("8", "-");
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크를 친 경우를 표현한다.")
    void strikeInFinalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(10));
        records.save(PitchResult.wrap(7));
        records.save(PitchResult.wrap(3));

        assertThat(records.export().getRecords()).containsExactly("X", "7", "/");
    }

    @Test
    @DisplayName("마지막 프레임에서 세 번 스트라이크 친 경우를 표현한다.")
    void strikeThreeTimesInFinalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(10));
        records.save(PitchResult.wrap(10));
        records.save(PitchResult.wrap(10));

        assertThat(records.export().getRecords()).containsExactly("X", "X", "X");
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어 처리한 상황을 표현한다.")
    void spareInFinalFrame() {
        Records records = new Records();
        records.save(PitchResult.wrap(7));
        records.save(PitchResult.wrap(3));
        records.save(PitchResult.wrap(10));

        assertThat(records.export().getRecords()).containsExactly("7", "/", "X");
    }

}
