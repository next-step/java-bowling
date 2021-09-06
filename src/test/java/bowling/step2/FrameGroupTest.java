package bowling.step2;

import bowling.step2.domain.FrameGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameGroupTest {

    @Nested
    @DisplayName("nextFrame 메소드는")
    class Describe_nextFrame {

        @Nested
        @DisplayName("이전까지 만들어진 frame의 갯수가 9개 이하라면")
        class Context_with_total_under_ten {
            @Test
            @DisplayName("테스트가 성공한다")
            public void makeNextFrame() {
                //given
                FrameGroup frameGroup = FrameGroup.of();

                //when
                for (int i = 0; i < 9; i++) {
                    frameGroup.nextFrame();
                }

                //then
                assertThat(frameGroup.currentSize()).isEqualTo(10);
            }
        }

        @Nested
        @DisplayName("이전까지 만들어진 frame의 갯수가 10개 이상이라면")
        class Context_with_total_over_ten {
            @Test
            @DisplayName("더이상 frame이 추가 되지 않고 10개로 유지된다")
            public void makeNextFrameFail() {
                //given
                FrameGroup frameGroup = FrameGroup.of();

                //when
                for (int i = 0; i < 20; i++) {
                    frameGroup.nextFrame();
                }

                //then
                assertThat(frameGroup.currentSize()).isEqualTo(10);
            }
        }
    }
}
