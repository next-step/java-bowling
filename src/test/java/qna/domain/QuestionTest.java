package qna.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Nested
    class delete_메서드는 {

        @Test
        void 삭제처리후_삭제기록을_리턴한다() {
            List<DeleteHistory> deleteHistories = Q1.delete();

            assertThat(Q1.isDeleted()).isTrue();
            assertThat(deleteHistories).containsExactly(
                    new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now())
            );
        }
    }
}
