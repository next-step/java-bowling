package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete() {
        Q1.delete();

        List<Answer> answerList = Q1.getAnswers();
        for(Answer answer: answerList) {
            assertThat(answer.isDeleted()).isEqualTo(true);
        }
    }
}
