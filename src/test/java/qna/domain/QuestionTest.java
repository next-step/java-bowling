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
    @DisplayName("내가 쓰지 않은 질문을 삭제하려고 할 경우, Exception을 반환해야 한다.")
    void assertIsOtherUsersQuestion() {
        assertThatThrownBy(() -> {
            Q1.assertUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage(Question.NO_DELETE_AUTHORITY_QUESTION_ERROR);
    }

    @Test
    @DisplayName("질문이 삭제가 잘 되었다면 deleted의 상태가 true가 되어야 한다.")
    void deleteQuestion() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
