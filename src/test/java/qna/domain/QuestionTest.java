package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Answer getAnswer(User user, Question question) {
        return new Answer(user, question, "Answers Contents");
    }

    @Test
    void 질문_삭제_성공() throws CannotDeleteException {
        Q1.addAnswer(getAnswer(UserTest.JAVAJIGI, Q1));

        List<DeleteHistory> histories = Q1.delete(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(histories).hasSize(2),
                () -> assertThat(Q1.isDeleted()).isTrue()
        );
    }

    @Test
    void 작성자가_다른_유저일_경우_삭제_시_예외_발생() {
        AtomicReference<List<DeleteHistory>> deleteHistories = new AtomicReference<>();
        assertAll(
                () -> assertThatThrownBy(() -> deleteHistories.set(Q2.delete(UserTest.JAVAJIGI)))
                        .hasMessage("질문을 삭제할 권한이 없습니다."),
                () -> assertThat(Q2.isDeleted()).isFalse(),
                () -> assertThat(deleteHistories.get()).isNull()
        );
    }

    @Test
    void 질문의_답변_중_다른_작성자가_적은_답변이_있을_경우_삭제_시_예외_발생() {
        Q2.addAnswer(getAnswer(UserTest.JAVAJIGI, Q2));

        AtomicReference<List<DeleteHistory>> deleteHistories = new AtomicReference<>();
        assertAll(
                () -> assertThatThrownBy(() -> deleteHistories.set(Q2.delete(UserTest.SANJIGI)))
                        .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertThat(Q2.isDeleted()).isFalse(),
                () -> assertThat(deleteHistories.get()).isNull()
        );
    }

}
