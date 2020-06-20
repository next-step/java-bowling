package bowling.domain;

import bowling.domain.bowling.BowlingScoreParser;
import bowling.domain.pitch.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingScoreParserTest {

    @DisplayName("현재 1번 투구한 경우 스트라이크면 X 반환")
    @Test
    public void parseScore_스트라이크() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(10);

        String result = BowlingScoreParser.parse(pitches);
        assertThat(result).isEqualTo("X  ");
    }

    @DisplayName("현재 1번 투구한 경우 0점이면 - 반환")
    @Test
    public void parseScore_거터() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(0);

        String result = BowlingScoreParser.parse(pitches);
        assertThat(result).isEqualTo("-  ");
    }

    @DisplayName("현재 1번 투구한 경우 1~9 점이면 숫자 반환")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7})
    public void parseScore_숫자(int hitCounts) {
        Pitches pitches = new Pitches();
        pitches.recordPitch(hitCounts);

        String result = BowlingScoreParser.parse(pitches);
        assertThat(result).isEqualTo(String.valueOf(hitCounts) + "  ");
    }

    @DisplayName("2번째 투구이며 총합 10점이면 스페어를 반환")
    @Test
    public void parseScore_두자리_스페어() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(0);
        pitches.recordPitch(10);

        String result = BowlingScoreParser.parse(pitches);
        assertThat(result).isEqualTo("-|/");
    }

    @DisplayName("2번째 투구이며 미스인 경우 숫자만을 출력")
    @Test
    public void parseScore_두자리_미스() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(3);
        pitches.recordPitch(6);

        String result = BowlingScoreParser.parse(pitches);
        assertThat(result).isEqualTo("3|6");
    }
}
