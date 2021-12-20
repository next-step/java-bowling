package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("삭제 이력을 남기기 위한 테스트")
public class DeleteHistoryTest {
    public static final DeleteHistory DH1 = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DH2 = new DeleteHistory(ContentType.ANSWER, 2L, UserTest.SANJIGI, LocalDateTime.now());

    @DisplayName("삭제 이력 대상 콘텐트 Id 확인")
    @Test
    void contentIdTest() {
        assertThat(DH1.contentId()).isEqualTo(1L);
    }
}
