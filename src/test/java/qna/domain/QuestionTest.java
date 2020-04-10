package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 =
            new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 =
            new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제")
    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistoryList = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistoryList.size()).isEqualTo(1);
    }

    @DisplayName("다른사람의 댓글이 있으면 삭제 실패")
    @Test
    void deleteFail() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    Q1.addAnswer(AnswerTest.A2);
                    Q1.delete(UserTest.JAVAJIGI);
                });
    }
}
