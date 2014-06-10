package ge.tsu.shared;

/**
 * Created by Vako on 07.06.2014.
 */
public class MedicException extends Exception {
    String errorMessage;

    public MedicException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MedicException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
