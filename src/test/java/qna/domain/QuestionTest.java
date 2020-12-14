package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(1,"title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2,"title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제하면 삭제 flag 만 false 로 바꾼다")
    @Test
    void delete(){
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        question.delete();
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("삭제하면 DeleteHistory 객체를 return 한다")
    @Test
    void deleteHistory(){
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        assertThat(question.delete()).isEqualTo(
                new DeleteHistory(ContentType.QUESTION, 1L, UserTest.SEHAN, LocalDateTime.now())
        );
    }


}
