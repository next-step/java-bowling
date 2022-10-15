package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void initQuestion() {
        this.Q1.getAnswers().clear();
    }

    @Test
    void shouldDeleteQuestion() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void shouldDeleteAnswers() {
        Answer answerA = getTestAnswer(Q1, "응답1");
        Answer answerB = getTestAnswer(Q1, "응답2");
        Q1.addAnswer(answerA);
        Q1.addAnswer(answerB);

        Q1.deleteAnswers();

        assertThat(answerA.isDeleted()).isTrue();
        assertThat(answerB.isDeleted()).isTrue();
    }

    @Test
    void shouldGetAnswerHistory() {
        Answer answerA = getTestAnswer(Q1, "응답1");
        Answer answerB = getTestAnswer(Q1, "응답2");
        Q1.addAnswer(answerA);
        Q1.addAnswer(answerB);

        List<DeleteHistory> histories = Q1.answerHistory();

        assertThat(histories).containsExactly(DeleteHistory.withAnswer(answerA), DeleteHistory.withAnswer(answerB));
    }

    private Answer getTestAnswer(Question question, String content) {
        return new Answer(new User("testId", "testpassword", "testname", "testmail"), question, content);
    }


}
