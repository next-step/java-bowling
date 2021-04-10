package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public Answer A1;
    public Answer A2;

    @BeforeEach
    void init() {
         Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
         Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
         A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
         A2 = new Answer(UserTest.SANJIGI, Q2, "Answers Contents2");
    }

    @DisplayName("삭제되면 답변의 상태도 삭제로 변경한다.")
    @Test
    void testCase1(){
        Answer answer = A1.delete();

        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자가 같다.")
    @Test
    void testCase2(){
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

}
