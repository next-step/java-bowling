package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void 로그인_사용자와_질문한_사람이_다를_경우_삭제할_수_없다() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_데이터를_삭제하면_상태가_삭제상태로_변경된다() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 질문_삭제_이력을_남긴다() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        DeleteHistory deleteHistory = deleteHistories.get(0);
        DeleteHistory otherDeleteHistory = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());

        assertThat(deleteHistory).isEqualTo(otherDeleteHistory);
    }

    @Test
    void 질문자와_답변자가_같으면_답변도_같이_삭제된다() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    void 질문자와_답변자가_다를_경우_삭제할_수_없다() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
