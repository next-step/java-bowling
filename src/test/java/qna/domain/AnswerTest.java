package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private Answer actual;

    @BeforeEach
    void setUp() {
        this.actual = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("Answer delete() 성공 후 DeleteHistory 객체 비교")
    void answer_deleteSuccess_isEqualTo() throws CannotDeleteException {
        assertThat(actual.delete(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, actual.getId(), actual.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("Answer delete() 다른 사람의 답변일 경우 CannotDeleteException 발생")
    void answer_deleteFailed_throwException() {
        assertThatThrownBy(() -> actual.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
