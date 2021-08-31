package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories();
    }

    @Test
    @DisplayName("답변 삭제 이력 추가")
    void add_answer() {
        //given

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

        //when
        deleteHistories.add(QuestionTest.Q1);
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

        //when
        deleteHistories.add(question);
        int actual = deleteHistories.size();

        //then
        assertThat(actual).isEqualTo(3);
    }


}
