package bowling.domain.referee;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.info.FinalFrameInfo;
import bowling.domain.frame.info.NormalFrameInfo;
import bowling.domain.score.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class RefereeTest {

    @ParameterizedTest(name = "프레임들의 라운드및 프레임 정보들을 확인 할 수 있다.")
    @CsvSource(
        {"10,12,2,true",
            "4,20,1,false",
            "5,21,2,true"})
    void writeAllFramesAndCheckScores(int downPins, int lastFrameNumber, int lastRound, boolean bonusFrame) {
        // Given
        Player givenPlayer = Player.from("PSH");
        Referee referee = Referee.create();
        referee.readyPerson(givenPlayer);

        int currentFrame = 0;

        do {
            referee.persons().forEach(player -> {
                Frames frames = referee.framesOfPerson(player);
                Frame frame = frames.latestFrame();
                if (frame.canRoll()) {
                    frame.roll(downPins);
                    frame.nextRound().ifPresent(frame1 -> referee.write(player, frame1));
                }
            });

            if (referee.isEndFrame(currentFrame)) {
                currentFrame++;
            }
        } while (currentFrame < 10);

        // When
        Frames frames = referee.framesOfPerson(givenPlayer);

        // Then
        assertThat(frames.size()).isEqualTo(lastFrameNumber);
        assertThat(frames.get(0).frameInfo()).isEqualTo(NormalFrameInfo.of(0, 0));
        assertThat(frames.get(0).numberOfDownedPins()).isEqualTo(Score.from(downPins));
        assertThat(frames.latestFrame().frameInfo()).isEqualTo(FinalFrameInfo.of(9, lastRound, bonusFrame));
    }


}

