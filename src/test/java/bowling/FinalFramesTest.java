package bowling;

import bowling.domain.FinalFrames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFramesTest {

    private FinalFrames finalFrames;

    @BeforeEach
    void setUp() {
        finalFrames = FinalFrames.of(new ArrayList<>());
    }

    @Test
    @DisplayName("마지막 프레임 테스트")
    void checkFinalFrame() {
        finalFrames.next(10);
        finalFrames.next(10);
        finalFrames.next(10);

        assertThat(finalFrames.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("마지막 프레임 보너스 게임 체크")
    void checkBonusGame() {
        finalFrames.next(5);
        finalFrames.next(4);
        assertThat(finalFrames.isBonus()).isFalse();

        FinalFrames finalFrames1 = FinalFrames.of(new ArrayList<>());
        finalFrames1.next(3);
        finalFrames1.next(7);
        assertThat(finalFrames1.isBonus()).isTrue();

        FinalFrames finalFrames2 = FinalFrames.of(new ArrayList<>());
        finalFrames2.next(10);
        finalFrames2.next(0);
        assertThat(finalFrames2.isBonus()).isTrue();

    }
}
