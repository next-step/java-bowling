package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    private Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    private Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("질문 삭제 성공 시 DeleteHistory 생성 성공 비교 테스트")
    @Test
    public void compare_deleteHistories() throws CannotDeleteException {
        Q1.addAnswer(A1);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        assertThat(Q1.delete(UserTest.JAVAJIGI))
                .isEqualTo(deleteHistories);
    }
}
