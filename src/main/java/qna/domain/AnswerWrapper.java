package qna.domain;

import java.util.List;

public class AnswerWrapper {
    private final List<Answer> answers;

    public AnswerWrapper(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    
    public void deleteAll(){
        
    }
}
