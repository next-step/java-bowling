package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("댓글이 없고, 본인 질문을 삭제를 시도하면 삭제상태는 True 이다")
    void deleteTrue() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인댓글만 있고, 본인 질문을 삭제를 시도하면 삭제상태는 True 이다")
    void deleteQuestionAndAnswerTrue() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("댓글이 없고, 다른사람 질문을 삭제를 시도하면 예외가 발생한다")
    void deleteOnlyQuestionException() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
