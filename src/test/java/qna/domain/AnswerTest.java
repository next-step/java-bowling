package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("작성자가 존재하지 않는 경우")
    void writerNull() {
        assertThatThrownBy(() -> {
            Answer answer = new Answer(1L, null, QuestionTest.Q1, "test");
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("질문자가 존재하지 않는 경우")
    void questionNull() {
        assertThatThrownBy(() -> {
            Answer answer = new Answer(1L, UserTest.JAVAJIGI, null, "test");
            answer.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("다른 사람이 답변이있다면 삭제할 수 없습니다.")
    void validateConfirmWriter() {
        assertThatThrownBy(() -> {
            Answer answer = new Answer(1L, UserTest.JAVAJIGI, new Question("title", "contents"), "test");
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변을 제거합니다.")
    void delete() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }


}
