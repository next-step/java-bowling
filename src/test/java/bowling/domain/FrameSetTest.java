package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameSetTest {
    @Test
    void from() {
        assertThat(FrameSet.from("yongdae")).isNotNull();
    }

    @Test
    void getBowler() {
        String bowler = "yongdae";
        assertThat(FrameSet.from(bowler).getBowler()).isEqualTo(bowler);
    }
}
