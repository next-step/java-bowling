package bowling.domain.record;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RecordTest {

    @Test
    public void 스트라이크인_경우() {
        Record result = Record.ofPinCount(10);
        assertThat(result).isEqualTo(Strike.getInstance());
    }

    @Test
    public void 스페어인_경우() {
        Record result = Record.ofPinCount(9).nextRecord(1);
        assertThat(result).isEqualTo(Spare.getInstance());
    }

    @Test
    public void 미스인_경우() {
        Record result = Record.ofPinCount(5);
        assertThat(result).isEqualTo(Miss.getInstance(5));
    }

    @Test
    public void 거터인_경우() {
        Record result = Record.ofPinCount(0);
        assertThat(result).isEqualTo(Gutter.getInstance());
    }
}