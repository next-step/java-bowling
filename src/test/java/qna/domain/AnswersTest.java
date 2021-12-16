package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @Test
    @DisplayName("삭제된 답변을 저장한다.")
    public void deleteHistory() {
        Answers.add(A1);
        List<DeleteHistory> deleteHistories = Answers.delete();
        assertThat(deleteHistories.size()).isNotZero();
    }

}
