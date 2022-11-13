package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-07
 */
public class AnswersTest {

    public static final Answers A1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
    public Answers a;

    @BeforeEach
    void setUp() {
        a = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "1"})
    void 삭제(int index) throws CannotDeleteException {
        a.deleteAll(AnswerTest.A1.getWriter());
        assertThat(a.get(index).isDeleted()).isTrue();
    }

    @Test
    void 모두삭제() throws CannotDeleteException {
        a.deleteAll(AnswerTest.A1.getWriter());
        assertThat(a.isDeletedAll()).isTrue();
    }

    @Test
    void 삭제이력_생성() throws CannotDeleteException {
        DeleteHistories histories = a.deleteAll(AnswerTest.A1.getWriter());
        assertThat(histories.size()).isEqualTo(2);
        assertThat(histories.get(0)).isEqualTo(DeleteHistoryTest.ANSWER_DELETEHISTORY);
    }
}
