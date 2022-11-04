package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswersTest {

    public static Answers answers;
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @BeforeEach
    void setAnswers() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A2);
        answers = new Answers(answerList);
    }

    @Test
    void allIsOwner() {
        assertThat(answers.allIsOwner(JAVAJIGI)).isFalse();
    }
}
