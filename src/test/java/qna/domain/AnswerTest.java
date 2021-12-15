package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void create() {
        assertThat(A1).isEqualTo(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    public void getContents() {
        assertThat("Answers Contents1").isEqualTo(A1.getContents());
    }

    @Test
    public void delete() {
        Answer answer = new Answer();
        assertThat(answer.isDeleted()).isFalse();
        answer.setDeleted(true);
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void writer() {
        assertThat(A2.getWriter()).isEqualTo(UserTest.SANJIGI);
        assertThat(A2.isOwner(UserTest.SANJIGI)).isTrue();
        assertThat(A2.isOwner(UserTest.JAVAJIGI)).isFalse();
    }
}
