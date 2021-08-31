package qna.service;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.QuestionTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionerManagementTest {

    @Test
    void delete() throws CannotDeleteException {

        List<Question> questions = new ArrayList<>();
        Question question;

        question = QuestionTest.Q1;

        Answer answer = A1;

        question.addAnswer(answer);

        questions.add(question);

        assertThat(QuestionerManagement.of(questions).deleteHistory(JAVAJIGI, 1L));
    }
}
