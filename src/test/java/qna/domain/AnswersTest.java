package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer B1 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("답변 작성자와 답변을 삭제하려는 사람이 같은 답변만 있을 경우 삭제 테스트")
    void allDeletedSameUser() throws CannotDeleteException {
        //given
        Answers answers = new Answers();
        for (int i = 0; i < 10; i++) {
            answers.add(A1);
        }

        //when
        answers.delete(UserTest.JAVAJIGI);

        //then
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 작성자와 답변을 삭제하려는 사람이 다른 답변이 하나라도 있을 경우 삭제 테스트")
    void allDeletedOtherUser() {
        //given
        Answers answers = new Answers();
        for (int i = 0; i < 10; i++) {
            answers.add(A1);
        }
        answers.add(B1);

        //then
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(Answer.MESSAGE_EXIST_ANSWER_BY_OTHER_USER);
    }

}