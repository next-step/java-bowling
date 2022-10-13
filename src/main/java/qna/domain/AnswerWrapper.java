package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerWrapper {
    private final List<Answer> answers;

    public AnswerWrapper(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    
    public List<DeleteHistory> deleteAll(){
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.getAnswers().forEach((answer)->{
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
        return deleteHistories;
    }
}
