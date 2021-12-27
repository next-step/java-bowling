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
    @DisplayName("Answers 삭제: 본인이 작성한 답변을 삭제할 경우")
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
    @DisplayName("Answers 삭제: 다른 사람이 작성한 답변을 삭제할 경우")
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