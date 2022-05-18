package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    private static final DeleteHistory H1 = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());
    private static final DeleteHistory H2 = new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now());

    @Test
    void 다른_사람의_질문_삭제() {
        assertThatThrownBy(() -> {
            Q1.delete(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 자신의_질문_삭제() {
        Q1.delete(Q1.getWriter());
    }

    @Test
    void 질문_삭제시_기록() {
        DeleteHistories deleteHistories = new DeleteHistories();
        List<DeleteHistory> expected = new ArrayList<>();
        assertThat(deleteHistories.getDeleteHistories()).isEqualTo(expected);

        Q1.record(deleteHistories, H1);
        expected.add(H1);
        assertThat(deleteHistories.getDeleteHistories()).isEqualTo(expected);
    }
}
