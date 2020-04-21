package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @Test
    public void deleteAnswersWhenUserIsOwner() throws CannotDeleteException {
        Answer answer1 = AnswerTest.ofUser(11L, UserTest.JAVAJIGI);
        Answers answers = new Answers(Arrays.asList(answer1));

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }
}
