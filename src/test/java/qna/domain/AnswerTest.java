package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete_된_답변확인() throws Exception {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void user가_일치할_경우() throws Exception {
        A1.delete();
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void user가_일치하지않을_경우() throws Exception {
        assertThat(A2.isOwner(UserTest.JAVAJIGI)).isFalse();
    }
}
