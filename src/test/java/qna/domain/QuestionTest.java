package qna.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteTest() {
        assertThat(Q1.isDeleted()).isFalse();
        assertThat(Q1.delete().isDeleted()).isTrue();
    }
}
