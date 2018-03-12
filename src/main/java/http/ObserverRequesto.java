package http;

public abstract class ObserverRequesto {
    public void update(String s) {
        System.out.println("Your response: " + s);
    }
}
