package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlerTest {

    @Test
    void constructor() {
        assertThat(Bowler.from("yongdae")).isEqualTo(Bowler.from("yongdae"));
    }

    @Test
    void valueOf() {
        String bowler = "yongdae";
        assertThat(Bowler.from(bowler).toString()).isEqualTo(bowler);
    }
}
