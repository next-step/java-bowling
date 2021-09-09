package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ScoreBoardTest {

    @DisplayName("스페어 처리시 보너스를 포함한 파이널 프레임을 계산 할 수 있다.")
    @Test
    void writeFinalFrame() {
        ScoreBoard scoreBoard = ScoreBoard.create();
        Person person = Person.from("Phobi");
        Frame fi = new FinalFrame(0, Pins.create(), false);
        scoreBoard.write(person, fi);

        while (fi.hasNextRound()) {
            fi = fi.roll(5);
            scoreBoard.write(person, fi);
        }

        Frames frames = scoreBoard.framesOfPerson(person);
        assertThat(frames.size()).isEqualTo(3);
    }
}