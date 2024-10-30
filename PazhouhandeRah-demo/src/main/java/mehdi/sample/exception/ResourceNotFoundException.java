package mehdi.sample.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String responseCode;

    public ResourceNotFoundException(String message, String responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }
}
