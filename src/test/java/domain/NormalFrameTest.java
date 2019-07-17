package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    void 다음프레임과_현재_상태를_가지는_프레임을_생성한다() {
        //given
        int frameNumber = 1;
        NormalFrame normalFrame = NormalFrame.of(frameNumber);

        //when
        //then
        assertThat(normalFrame).isNotNull();
    }

    @Test
    void 프레임번호가_9가_아니면_다음_번호를_가진_NormalFrame을_생성한다() {
        //given
        int frameNumber = 8;
        NormalFrame normalFrame = NormalFrame.of(frameNumber);

        //when
        NormalFrame generatedFrame = (NormalFrame) normalFrame.generateNextFrame();

        //then
        assertThat(generatedFrame.getIndex()).isEqualTo(frameNumber + 1);
    }

    @Test
    void 프레임번호가_9인_경우_다음_프레임은_FinalFrame으로_생성한다() {
        //given
        int frameNumber = 9;
        NormalFrame normalFrame = NormalFrame.of(frameNumber);

        //when
        Frame generatedFrame = normalFrame.generateNextFrame();

        //then
        assertThat(generatedFrame instanceof FinalFrame).isTrue();
    }
}
