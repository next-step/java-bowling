package qna.domain;

import javax.persistence.Lob;

public class QuestionBody {
    private final String title;
    
    @Lob
    private final String contents;
    
    public QuestionBody(final String title, final String contents) {
        this.title = title;
        this.contents = contents;
    }
    
    @Override
    public String toString() {
        return "QuestionBody{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
