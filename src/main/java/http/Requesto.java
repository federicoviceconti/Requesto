package http;

public class Requesto extends ObserverRequesto{
    private String response;

    @Override
    public void update(String s) {
        super.update(s);
        this.response = s;
    }

    public String getResponse() {
        return response;
    }
}
