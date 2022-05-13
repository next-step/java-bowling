package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private static final Answers ANSWERS1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
    private static final Answers ANSWERS2 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A3));

    private final User user = UserTest.JAVAJIGI;

    @Test
    void 답변목록_삭제_성공() {
        ANSWERS2.delete(user);
        assertThat(ANSWERS2.getAnswers().get(0).isDeleted()).isEqualTo(true);
        assertThat(ANSWERS2.getAnswers().get(1).isDeleted()).isEqualTo(true);
    }

    @Test
    void 답변목록_삭제_실패() {
        assertThatThrownBy(() -> {
            ANSWERS1.delete(user);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변목록_삭제시_기록() {
        DeleteHistories deleteHistories = new DeleteHistories();
        List<DeleteHistory> expected = new ArrayList<>();
        assertThat(deleteHistories.getDeleteHistories()).isEqualTo(expected);

        ANSWERS1.record(deleteHistories);
        expected.add(AnswerTest.H1);
        expected.add(AnswerTest.H2);
        assertThat(deleteHistories.getDeleteHistories()).isEqualTo(expected);
    }
}