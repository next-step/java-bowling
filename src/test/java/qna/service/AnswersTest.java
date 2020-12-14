package qna.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.UserTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @DisplayName("작성자가 아닌 사람이 작성한 댓글이 있으면 true 를 return 한다")
    @Test
    void hasAnswerOfOthers1(){
        Question questionOfSehan = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        Answer answerOfTheOther = new Answer(UserTest.JAVAJIGI, questionOfSehan, "Answers Contents1");

        assertThat(new Answers(questionOfSehan, Arrays.asList(answerOfTheOther)).hasAnswerOfOthers())
                .isTrue();
    }

    @DisplayName("작성자가 아닌 사람이 작성한 댓글이 없으면 false 를 return 한다")
    @Test
    void hasAnswerOfOthers2(){
        Question questionOfSehan = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        Answer answerOfSehan = new Answer(UserTest.SEHAN, questionOfSehan, "Answers Contents1");

        assertThat(new Answers(questionOfSehan, Arrays.asList(answerOfSehan)).hasAnswerOfOthers())
                .isFalse();
    }

    @DisplayName("댓글이 없으면 false 를 return 한다")
    @ParameterizedTest
    @NullAndEmptySource
    void hasAnswerOfOthers3(List<Answer> answers){
        Question questionOfSehan = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        assertThat(new Answers(questionOfSehan,answers).hasAnswerOfOthers())
                .isFalse();
    }

}