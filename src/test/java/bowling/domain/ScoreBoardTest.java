package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ScoreBoardTest {

    @DisplayName("final Frame에서 2구 내에 스페어 처리시 bonus frame의 점수들을 확인 할 수 있다.")
    @Test
    void writeFinalFrame() {
        // Given
        ScoreBoard scoreBoard = ScoreBoard.create();
        Person person = Person.from("PSH");
        Frame fi = FinalFrame.create();

        while (fi.hasNextRound()) {
            scoreBoard.write(person, fi);
            fi = fi.roll(5);
        }

        // When
        Frames frames = scoreBoard.framesOfPerson(person);
        Frame bonusFrame = frames.get(2);

        // Then
        assertThat(frames.size()).isEqualTo(3);
        assertThat(bonusFrame.numberOfDownedPins()).isEqualTo(5);
    }
}
