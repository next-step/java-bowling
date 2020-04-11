package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question("title3", "this question has no answer").writeBy(UserTest.JAVAJIGI);

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

    @Test
    @DisplayName("Question의 삭제 히스토리를 1개 생성하여 잘 생성되었는지 테스트한다.")
    void questionHistory() {
        assertThat(Q1.getDeleteQuestionHistory()).isNotNull();
    }

    @Test
    @DisplayName("댓글이 없는 질문이 삭제가 잘 되었다면 삭제 히스토리가 1개 반환되어야 한다.")
    void questionDeleteHistory() throws CannotDeleteException {
        assertThat(Q3.delete(UserTest.JAVAJIGI).size()).isEqualTo(1);
    }
}
