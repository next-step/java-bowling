package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("글의 상태를 삭제로 바꾼다.")
    @Test
    void delete() throws CannotDeleteException {

        assertThat(A1.isDeleted()).isFalse();

        A1.deleteByUser(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("글쓴이가 아니면 에러가 발생한다.")
    @Test
    void validWriterException() {
        assertThatThrownBy(() -> A1.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("글을 삭제하면 삭제기록을 반환한다.")
    @Test
    void successDeleted() throws CannotDeleteException {
        assertThat(A1.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(DeleteHistory.class);
        assertThat(A2.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(DeleteHistory.class);
    }
}
