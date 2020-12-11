package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.SANJIGI;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("답변 작성자가 아닐경우 CannotDeleteException")
    @Test
    void throwCannotDeleteException() {
        assertThatThrownBy(() -> A1.validateAnswerOwner(SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("삭제 메서드 실행 시 answer 삭제 플래그 true,  DeleteHistories 객체 반환")
    @Test
    void deleteQuestion() throws Exception {
        DeleteHistory deleteResult = A2.delete(SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
        assertThat(deleteResult).isEqualTo(new DeleteHistory(ContentType.ANSWER, A2.getId(), SANJIGI, LocalDateTime.now()));
    }
}
