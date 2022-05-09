package bowling.domain.score;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.referee.Referee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoresTest {

    @ParameterizedTest(name = "쓰러뜨린 핀의 개수대로 라스트 점수를 확인 할 수 있다.")
    @CsvSource(
        {"4,80",
            "10,300",
            "5,150"})
    void calcScore(int countPinsDown, int givenLastScore) {
        // Given
        Player givenPlayer = Player.from("PSH");
        Referee referee = Referee.create();
        referee.readyPerson(givenPlayer);

        int currentFrame = 0;

        Frames givenFrames = referee.framesOfPerson(givenPlayer);

        do {
            Frame frame = givenFrames.latestFrame();

            if (frame.canRoll()) {
                frame.roll(countPinsDown);
                frame.nextRound().ifPresent(frame1 -> referee.write(givenPlayer, frame1));
            }

            if (referee.isEndFrame(currentFrame)) {
                currentFrame++;
            }
        } while (currentFrame < 10);

        // When
        Scores actual = givenFrames.scores();

        // Then
        List<Score> scores = actual.scores();
        assertThat(scores.stream().mapToInt(Score::score).sum()).isEqualTo(givenLastScore);
    }
}