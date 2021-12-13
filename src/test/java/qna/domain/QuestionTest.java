package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void to_delete() {
        assertThat(Q1.isDeleted()).isFalse();
        assertThat(Q1.toDeleted().isDeleted()).isTrue();
    }

    @Test
    void get_delete_history() {
        assertThat(Q2.getDeleteHistory().size()).isEqualTo(0);

        Q2.toDeleted();
        assertThat(Q2.getDeleteHistory().size()).isEqualTo(1);
    }

    @Test
    void get_delete_history_with_answers() {
        Q2.addAnswer(AnswerTest.A1);
        Q2.toDeleted();
        assertThat(Q2.getDeleteHistory().size()).isEqualTo(2);
    }
}
