package qna.service;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.QuestionTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionerTest {

    @Test
    void findQuestioner() throws CannotDeleteException {

        List<Question> questions = new ArrayList<>();

        questions.add(new Question(1L, "title1", "contents1").writeBy(JAVAJIGI));
        assertThatThrownBy(() ->
                Questioners.of(questions).findQuestoner(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);

    }

    @Test
    void checkAnotherAnswer() throws CannotDeleteException {

        List<Question> questions = new ArrayList<>();
        Question question;

        question = QuestionTest.Q1;

        Answer answer = A1;

        question.addAnswer(answer);

        questions.add(question);


        assertThatThrownBy(() ->
                Questioners.of(questions).findAnswer(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);

    }
}
