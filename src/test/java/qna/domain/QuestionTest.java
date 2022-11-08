package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void testDelete() {
        List<DeleteHistory> deleteHistories = Q1.delete();

        assertThat(Q1.isDeleted()).isTrue();
        deleteHistories.forEach(deleteHistory -> assertThat(deleteHistory.isDeletedBy(Q1.getWriter())).isTrue());
    }
}
