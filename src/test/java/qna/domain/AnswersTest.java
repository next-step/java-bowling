package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer B1 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("Answer 전체 삭제: 작성자와 답변자가 동일할 경우")
    void allDeletedSameUser() throws CannotDeleteException {
        //given
        List<Answer> collect = Collections.singletonList(A1);
        Answers answers = new Answers(collect);

        //when
        answers.delete(UserTest.JAVAJIGI);

        //then
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Answer 전체 삭제: 작성자와 답변자가 다를 경우")
    void allDeletedOtherUser() {
        //given
        List<Answer> collect = Collections.singletonList(B1);
        Answers answers = new Answers(collect);
        QuestionTest.Q2.setAnswers(answers);

        //then
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(Answers.MESSAGE_EXIST_ANSWER_BY_OTHER_USER);
    }

}