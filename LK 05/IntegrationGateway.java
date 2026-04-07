public class IntegrationGateway<T extends MedicalRecord & Versioned & Confidential & CopyData<T>> {
    private T data;

    public IntegrationGateway(T data) {
        this.data = data;
    }

    public SecureResponse<T> fetchData(String patientId, int clearanceLevel) {

        if (!data.getPatientId().equals(patientId)) {
            return new SecureResponse<>(false, null, "Patient not found");
        }

        String warning = null;
        T safeData = data.copy();

        if (clearanceLevel < safeData.getSecurityLevel()) {
            safeData.maskSensitiveData();
            warning = "Data masked due to low clearance";
        }

        return new SecureResponse<>(true, safeData, warning);
    }
}