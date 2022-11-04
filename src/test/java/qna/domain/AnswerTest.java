package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "content");
    }

    @DisplayName("사용자가 답변자가 아닌 경우 UnAuthorizedException 예외를 throw한다.")
    @Test
    void validate_writer() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("질문의 상태를 삭제 상태로 변경한다.")
    @Test
    void set_delete() {
        Assertions.assertAll(
                () -> assertThat(answer.isDeleted()).isFalse(),
                () -> assertThat(answer.setDeleted(true).isDeleted()).isTrue(),
                () -> assertThat(answer.setDeleted(false).isDeleted()).isFalse()
        );
    }

    @DisplayName("답변 삭제시 삭제이력을 남긴다.")
    @Test
    void delete_deleteHistory() {
        DeleteHistory expected
                = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());

        assertThat(answer.delete(UserTest.JAVAJIGI)).isEqualTo(expected);
    }
}
