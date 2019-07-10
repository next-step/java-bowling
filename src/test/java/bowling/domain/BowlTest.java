package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 19:10
 */
class BowlTest {
    @DisplayName("스트라이크 enum 반환")
    @Test
    void strikeEnum() {
        Bowl bowl = Bowl.check(10, true);
        assertThat(bowl.getScoreDisplay()).isEqualTo("X");
    }

    @DisplayName("스패어 enum 반환")
    @Test
    void spareEnum() {
        Bowl bowl = Bowl.check(10, false);
        assertThat(bowl.getScoreDisplay()).isEqualTo("/");
    }

    @DisplayName("거터 enum 반환")
    @Test
    void gutterEnum() {
        Bowl bowl = Bowl.check(0, false);
        assertThat(bowl.getScoreDisplay()).isEqualTo("-");
    }

    @DisplayName("미스 enum 반환")
    @Test
    void missEnum() {
        Bowl bowl = Bowl.check(7, false);
        assertThat(bowl.getScoreDisplay()).isEqualTo("7");
    }
}