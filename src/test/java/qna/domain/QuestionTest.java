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
    void 질문_작성자가_아닌_유저가_삭제할_때() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 정상적으로_삭제되는_경우_isDeleted_로_상태_변경() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q2.delete(UserTest.SANJIGI);
        assertThat(deleteHistories).contains(
                new DeleteHistory(
                        ContentType.QUESTION, Q2.getId(), UserTest.SANJIGI, LocalDateTime.now()
                )
        );
    }
}
