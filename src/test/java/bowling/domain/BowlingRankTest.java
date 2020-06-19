package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingRankTest {

    @DisplayName("Rank 반환 : Strike")
    @Test
    public void valueOf_스트라이크() {
        BowlingRank rank = BowlingRank.valueOf(10, 0);

        assertThat(rank).isEqualTo(BowlingRank.STRIKE);
    }
}
