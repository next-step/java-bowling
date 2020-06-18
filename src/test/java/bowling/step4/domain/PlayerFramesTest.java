package bowling.step4.domain;

import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.domain.scores.NormalScores;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerFramesTest {
    @DisplayName("Frame의 길이와 관계 없이 10개의 프레임을 출력하는지 확인하는 테스트")
    @Test
    public void Preview_테스트 () {
        assertEquals(
            10,
            PlayerFrames.of(
                Player.valueOf("hji"),
                NormalFrame.of(1, NormalScores.init(), null)
            ).getPreview().count()
        );
    }
}