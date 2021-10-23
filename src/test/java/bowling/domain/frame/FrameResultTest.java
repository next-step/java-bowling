package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FrameResultTest {

    @Test
    @DisplayName("이전 totalScore를 받아 현재 totalScore를 변환할 수 있다.")
    void addTotalScoreTest() {

        // given
        int beforeTotalScore = 20;

        FrameResult expected = FrameResult.of(30, 10, "X");

        // when
        FrameResult result = FrameResult.ofScoreAndDesc(10, "X");
        result.addTotalScore(beforeTotalScore);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 score가 계산 불가능한 상황이면 totalScore를 더하지 않는다.")
    void notAddTotalScoreTest() {

        // given
        int beforeTotalScore = 20;

        FrameResult expected = FrameResult.of(-1, -1, "");

        // when
        FrameResult result = FrameResult.ofScoreAndDesc(-1, "");
        result.addTotalScore(beforeTotalScore);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Nested
    @DisplayName("totalSocre String 반환 기능")
    class totalScoreToStringTest {

        @Test
        @DisplayName("total score가 계산 가능한 상황이면 반환해야한다.")
        void success() {

            // given
            FrameResult frameResult = FrameResult.of(30, 10, "X");

            String expected = "30";

            // when
            String result = frameResult.totalScoreToString();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("total score가 계산 불가능한 상황이면 공백을 반환해야 한다.")
        void fail() {

            // given
            FrameResult frameResult = FrameResult.of(-1, 10, "X");

            String expected = "";

            // when
            String result = frameResult.totalScoreToString();

            // then
            assertThat(result).isEqualTo(expected);
        }
    }

}
