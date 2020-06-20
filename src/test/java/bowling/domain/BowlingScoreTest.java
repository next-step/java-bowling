package bowling.domain;

import bowling.domain.bowling.BowlingScore;
import bowling.domain.pitch.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BowlingScoreTest {

    @DisplayName("BowlingScore 객체 생성")
    @Test
    public void makeBowlingScore_정상() {
        Pitches pitches = new Pitches();

        assertThatCode(() -> {
            new BowlingScore(pitches);
        }).doesNotThrowAnyException();
    }

    @DisplayName("현재 1번 투구한 경우 스트라이크면 X 반환")
    @Test
    public void getScore_스트라이크() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(10);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String score = bowlingScore.getScore();

        assertThat(score).isEqualTo("X");
    }

    @DisplayName("현재 1번 투구한 경우 Gutter이면 -반환")
    @Test
    public void getScore_거터() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(0);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String score = bowlingScore.getScore();

        assertThat(score).isEqualTo("-");
    }

    @DisplayName("현재 1번 투구한 경우 1~9 점이면 숫자 반환")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7})
    public void getScore_숫자(int hitCounts) {
        Pitches pitches = new Pitches();
        pitches.recordPitch(hitCounts);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String result = bowlingScore.getScore();

        assertThat(result).isEqualTo(String.valueOf(hitCounts));
    }

    @DisplayName("2번째 투구이며 총합 10점이면 스페어를 반환")
    @Test
    public void getScore_두자리_스페어() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(0);
        pitches.recordPitch(10);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String result = bowlingScore.getScore();

        assertThat(result).isEqualTo("-|/");
    }

    @DisplayName("2번째 투구이며 미스인 경우 숫자만을 출력")
    @Test
    public void parseScore_두자리_미스() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(3);
        pitches.recordPitch(6);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String result = bowlingScore.getScore();

        assertThat(result).isEqualTo("3|6");
    }

    @DisplayName("보너스까지 투구하는 경우 : 스페어 + 미스")
    @Test
    public void parseSocre_스페어_미스() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(3);
        pitches.recordPitch(7);
        pitches.recordPitch(0);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String result = bowlingScore.getScore();

        assertThat(result).isEqualTo("3|/|-");
    }

    @DisplayName("보너스까지 투구하는 경우 : 스트라이크 + 스트라이크")
    @Test
    public void parseSocre_스트라이크_스트라이크() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(10);
        pitches.recordPitch(10);
        BowlingScore bowlingScore = new BowlingScore(pitches);

        String result = bowlingScore.getScore();

        assertThat(result).isEqualTo("X|X");
    }
}
