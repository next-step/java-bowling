package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import qna.CannotDeleteException;
import qna.ForbiddenException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.Fixtures.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class QuestionTest {
    @Test
    public void deleteQuestion() throws CannotDeleteException {
        //given
        Question question = aQuestion1();

        //when
        question.delete(aUser1());

        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void deleteQuestionFail() throws CannotDeleteException {
        //given
        Question question = aQuestion1();

        //when, then
        assertThatThrownBy(() -> {
            question.delete(aUser2());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteQuestionWithAnswer() throws CannotDeleteException {
        //given
        Question question = aQuestion1();
        Answer answer = aAnswer1();
        question.addAnswer(answer);

        //when
        question.delete(aUser1());

        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void deleteQuestionWithAnswerFail() throws CannotDeleteException {
        //given
        Question question = aQuestion1();
        Answer answer = aAnswer2();
        question.addAnswer(answer);

        //when, then
        assertThatThrownBy(() -> {
            question.delete(aUser1());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
