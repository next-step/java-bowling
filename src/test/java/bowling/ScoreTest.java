package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("create 생성 테스트")
    void When_Create_Then_Created() {
        assertThat(Score.create(0)).isEqualTo(Score.create(0));
    }

    @Test
    @DisplayName("createNotDetermined 생성 테스트")
    void When_NotDetermined_Then_Created() {
        assertThat(Score.createNotDetermined()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Add 테스트")
    void When_Add_Then_Added() {
        Score score = Score.create(1);

        assertThat(score.add(Score.create(2))).isEqualTo(Score.create(3));
    }

    @Test
    @DisplayName("NotDetermined와 Score를 더하면 항상 NotDetermined")
    void Given_NotDetermined_When_Add_Then_NotDetermined() {
        Score score = Score.createNotDetermined();

        assertThat(score.add(Score.create(2))).isEqualTo(Score.createNotDetermined());
    }
}
