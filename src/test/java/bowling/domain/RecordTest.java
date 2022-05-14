package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecordTest {
    private Record given_record = new Record();

    @Test
    void 저장된_기록_일종의_불변성_확인() {
        given_record.record(3);
        given_record.record(10);
        given_record.record(9);

        assertThatThrownBy(() -> given_record.getRecords().set(1, 9))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> given_record.getRecords().add(4))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> given_record.getRecords().remove(2))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}