package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("답변 삭제 이력 추가")
    void add_answer() {
        //given
        DeleteHistories deleteHistories = DeleteHistories.of(AnswersTest.ANSWERS1);

        //when
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("질문 삭제 이력 추가")
    void add_question() {
        //given
        DeleteHistories deleteHistories = DeleteHistories.of(QuestionTest.Q1);

        //when
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("질문 및 답변 삭제 이력 추가")
    void add_question_and_answer() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));

        DeleteHistories deleteHistories = DeleteHistories.of(question);

        //when
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(3);
    }


}
