package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("답변 삭제 이력 추가")
    void add_answer() {
        //given
        DeleteHistories deleteHistories = new DeleteHistories();

        //when
        deleteHistories.add(AnswersTest.ANSWERS1);
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(2);
    }


    @Test
    @DisplayName("질문 삭제 이력 추가")
    void add_question() {
        //given
        DeleteHistories deleteHistories = new DeleteHistories();

        //when
        deleteHistories.add(QuestionTest.Q1);
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(1);
    }


}
