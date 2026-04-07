public class PatientProfileV1 implements MedicalRecord, Versioned, Confidential, CopyData<PatientProfileV1> {
    private String patientId;
    private String name;
    private String nik;
    private int securityLevel = 2; // Default security level for patient profiles

    public PatientProfileV1(String patientId, String name, String nik) {
        this.patientId = patientId;
        this.name = name;
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public String getNik() {
        return nik;
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public int getVersion() {
        return 1; // This is version 1 of the patient profile
    }

    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }

    @Override
    public void maskSensitiveData() {
        if (nik != null) {
            this.nik = "****************"; // Mask the entire NIK for V1 if doctor's level are lower than 2
        }
    }

    @Override
    public PatientProfileV1 copy() {
        return new PatientProfileV1(patientId, name, nik);
    }

    @Override
    public String toString() {
        return "Patient Profile" +
                "\nPatientId: " + patientId +
                "\nName: " + name +
                "\nNIK: " + nik +
                "\nSecurityLevel: " + securityLevel;
    }
}
