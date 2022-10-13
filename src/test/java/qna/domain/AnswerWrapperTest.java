package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswerWrapperTest {
    private static AnswerWrapper answerWrapper = new AnswerWrapper(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("답글을 삭제할 경우 삭제한 답글의 삭제 값이 true 가 되는지 테스트")
    void deleteAllTest1() {
        answerWrapper.deleteAll();
        answerWrapper.getAnswers().forEach((answer)-> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("답글을 삭제할 경우 삭제한 답글 개수만큼의 삭제 히스토리가 반환되는지 테스트")
    void deleteAllTest2() {
        List<DeleteHistory> deleteHistorys = answerWrapper.deleteAll();
        assertThat(deleteHistorys).hasSize(answerWrapper.getAnswers().size());
    }
}