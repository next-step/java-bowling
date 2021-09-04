package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 불가능")
    @Test
    void delete_isOwner_error() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.delete(UserTest.SANJIGI));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    @Test
    void delete_isOwner() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
        assertThat(A2.delete(UserTest.SANJIGI).isDeleted()).isTrue();
    }
}
