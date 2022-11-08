package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 질문_작성자가_아닌_유저가_삭제할_경우() {
        assertThatThrownBy(() -> Q1.deleteWithAnswer(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문_작성자가_삭제할_경우() {
        assertThatNoException().isThrownBy(() -> Q2.deleteWithAnswer(UserTest.SANJIGI));
    }

    @Test
    void 정상적으로_삭제할_경우_isDeleted_상태_변경() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.deleteWithAnswer(UserTest.JAVAJIGI);
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
