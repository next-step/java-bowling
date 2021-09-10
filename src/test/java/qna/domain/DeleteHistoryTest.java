package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {

    @DisplayName("답변 삭제 성공 시 DeleteHistory 생성 성공 비교 테스트")
    @Test
    public void compare_deleteHistory() throws CannotDeleteException {
        assertThat(AnswerTest.A1.delete(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
