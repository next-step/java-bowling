package bowling.domain.engine;

import bowling.domain.concrete.FinalFrame;
import bowling.domain.concrete.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameCreatorTest {

    @Test
    @DisplayName("아홉 번째 프레임까지는 일반 프레임을 생성한다.")
    void createNormalFrames() {
        FrameCreator creator = new FrameCreator();

        for (int i = 0; i < 9; i++){
            assertThat(creator.create()).isInstanceOf(NormalFrame.class);
        }
    }

    @Test
    @DisplayName("열 번째 프레임은 특수 프레임을 생성한다.")
    void createFinalFrame() {
        FrameCreator creator = new FrameCreator();

        for (int i = 0; i < 9; i++){
            creator.create();
        }

        assertThat(creator.create()).isInstanceOf(FinalFrame.class);
    }
        
}
