package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void isOwner_테스트() {
        Question q = new Question();
        Answer answer = new Answer(1L, new User(2L, "1", "12","name","email"), q, "content");
        assertThat(answer.isOwner(new User(1L, "2", "23", "name1", "email1"))).isFalse();
    }
}
