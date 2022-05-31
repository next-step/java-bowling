package qna.domain;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 답변_없는_질문_삭제_테스트() throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));

        assertThat(Q1.delete(UserTest.JAVAJIGI)).isEqualTo(deleteHistories);
    }

    @Test
    void 질문자가_작성한_답변_있는_질문_삭제_테스트() throws CannotDeleteException {
        Q1.addAnswer(A1);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        assertThat(Q1.delete(UserTest.JAVAJIGI)).isEqualTo(deleteHistories);
    }

    @Test
    void 질문자가_작성하지_않은_답변_있는_질문_삭제_테스트() {
        Q1.addAnswer(A2);

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 다른_사람이_작성한_질문_삭제_테스트() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageMatching("질문을 삭제할 권한이 없습니다.");
    }
}
