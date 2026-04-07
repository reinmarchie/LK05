public class SecureResponse<T extends MedicalRecord & Confidential> {
    private boolean success;
    private T data;
    private String warningMessage;

    public SecureResponse(boolean success, T data, String warningMessage) {
        this.success = success;
        this.data = data;
        this.warningMessage = warningMessage;
    }

    public void display() {
        System.out.println("Success: " + success);
        System.out.println("Data: " + data);
        if (warningMessage != null) {
            System.out.println("Warning: " + warningMessage);
        }
        System.out.println("-------------------------");   
    }
}