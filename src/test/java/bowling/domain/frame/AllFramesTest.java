package bowling.domain.frame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bowling.domain.Fixture.END_GAME_STATE_EXAMPLE1;
import static bowling.domain.Fixture.END_GAME_STATE_EXAMPLE2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AllFramesTest {
    List<String> playerNames;

    @BeforeAll
    void setUp() {
        playerNames = Arrays.asList("KCS", "KJY");
    }

    @DisplayName("정적 팩토리 메서드 from에, 플레이어별 프레임 리스트와, 이름을 인자로 받으면, AllFrames 클래스 객체를 생성한다")
    @Test
    void createTest() {
        assertThat(AllFrames.from(new ArrayList<>(), playerNames.size())).isInstanceOf(AllFrames.class);
    }

    @DisplayName("1123")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() ->
            AllFrames.from(null, playerNames.size())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임이 종료되는 상황인 데이터를 입력 받으면, isGameFinish함수는 참을 반환한다")
    @Test
    void isGameFinishTest() {
        AllFrames allFrames = AllFrames.from(
            Arrays.asList(Frames.from(END_GAME_STATE_EXAMPLE1), Frames.from(END_GAME_STATE_EXAMPLE2)), playerNames.size());
        assertThat(allFrames.isGameFinish()).isTrue();
    }
}
