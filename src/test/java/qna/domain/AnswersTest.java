package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static qna.domain.AnswerTest.*;

public class AnswersTest {

    @DisplayName("Delete is succeeded when write tries to delete answers")
    @Test
    void testDeleteSuccess() throws CannotDeleteException {
        Answers answersByJavajigi = new Answers(Arrays.asList(A1, A3));
        assertThat(answersByJavajigi.delete(UserTest.JAVAJIGI)).isEqualTo(Arrays.asList(DELETE_HISTORY_A1, DELETE_HISTORY_A3));
        Answers answersBySanjigi = new Answers(Arrays.asList(A2, A4));
        assertThat(answersBySanjigi.delete(UserTest.SANJIGI)).isEqualTo(Arrays.asList(DELETE_HISTORY_A2, DELETE_HISTORY_A4));
    }

    @DisplayName("Delete is failed when non-writer tries to delete answers")
    @Test
    void testDeleteFail() {
        Answers answers = new Answers(Arrays.asList(A1, A2));
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
