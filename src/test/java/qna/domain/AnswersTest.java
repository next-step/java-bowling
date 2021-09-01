package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Answers answers = new Answers();

    @Before
    public void setUp() {
        answers.add(AnswerTest.A1);
    }

    @Test
    public void delete() throws CannotDeleteException {
        answers.delete(UserTest.JAVAJIGI);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @DisplayName("다른 유저의 답변이 있는 경우 삭제 실패")
    @Test
    public void delete_fail() {
        answers.add(AnswerTest.A2);
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}