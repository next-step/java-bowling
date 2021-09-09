package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.info.FinalFrameInfo;
import bowling.domain.frame.info.NormalFrameInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ScoreBoardTest {

    @DisplayName("final Frame구간에서 2구 내에 스페어 처리시 bonus frame의 점수들을 확인 할 수 있다.")
    @Test
    void checkBoardScoreFinalFrame() {
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


    @DisplayName("프레임들의 라운드및 프레임 정보들을 확인 할 수 있다.")
    @Test
    void writeAllFramesAndCheckScores() {
        // Given
        ScoreBoard scoreBoard = ScoreBoard.create();
        Person person = Person.from("PSH");
        Frame fi = NormalFrame.create();

        int STRIKE = 10;

        for (int i = 0; i < 10; i++) {
            while (fi.hasNextRound()) {
                scoreBoard.write(person, fi);
                fi = fi.roll(STRIKE);
            }
        }

        // When
        Frames frames = scoreBoard.framesOfPerson(person);

        // Then
        int MAX_FRAME = 12;
        assertThat(frames.size()).isEqualTo(MAX_FRAME);
        assertThat(frames.get(0).frameInfo()).isEqualTo(NormalFrameInfo.of(0, 0));
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(10);
        assertThat(frames.get(11).frameInfo()).isEqualTo(FinalFrameInfo.of(9, 2, true));
    }
}
