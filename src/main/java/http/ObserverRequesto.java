package http;

import response.Response;

public abstract class ObserverRequesto {
    private Response response;

    public void update(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
