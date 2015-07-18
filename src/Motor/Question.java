package Motor;

public class Question {
    private String text = null;
    private Object responses = null;

    Question(String text, Object responses) {
        this.text = text;
        this.responses = responses;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getResponses() {
        return responses;
    }

    public void setResponses(Object responses) {
        this.responses = responses;
    }
} 