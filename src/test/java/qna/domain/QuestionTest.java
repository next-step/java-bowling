package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인한 사람과 Question Owner가 다른 경우 삭제 실패 테스트")
    @Test
    public void delete_error() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인한 사람과 Question Owner가 같은 경우 삭제 성공 테스트")
    @Test
    public void delete_success() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("Question에 Answer Owner가 다른 경우 삭제 실패 테스트")
    @Test
    public void delete_answer_error() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("Question에 Answer Owner가 같은 경우 삭제 성공 테스트")
    @Test
    public void compare_deleteHistory() throws CannotDeleteException {
        Q2.addAnswer(AnswerTest.A2);
        Q2.delete(UserTest.SANJIGI);

        assertThat(Q2.isDeleted()).isTrue();
    }
}
