package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @DisplayName("로그인 유저가 작성한 답변만 있을경우 삭제가 실행된다.")
    @Test
    void delete() throws CannotDeleteException {
        Answers answers1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A1));
        List<DeleteHistory> deleteHistoryList = answers1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistoryList).hasSize(2);
    }
}
