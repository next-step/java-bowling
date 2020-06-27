package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void validate() {
        assertThatThrownBy(() -> Q1.validate(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_deleted이_true() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_삭제시_deleteHistory_확인() {
        List<DeleteHistory> deleteHistories = Q1.delete();
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(deleteHistories).contains(deleteHistory);
    }
}
