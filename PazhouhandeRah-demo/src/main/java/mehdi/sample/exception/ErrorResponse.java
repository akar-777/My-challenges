package mehdi.sample.exception;

public class ErrorResponse {
    private int status;
    private String message;
    private String responseCode;

    public ErrorResponse(int status, String message, String responseCode) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getResponseCode() {
        return responseCode;
    }
}
