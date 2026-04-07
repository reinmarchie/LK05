public class PatientProfileV2 implements MedicalRecord, Versioned, Confidential, CopyData<PatientProfileV2> {
    private String patientId;
    private String name;
    private String nik;
    private String allergy; //new field added in V2
    private String surgeryHistory; //new field added in V2
    private int securityLevel = 3; // Increased security level for V2

    public PatientProfileV2(String patientId, String name, String nik, String allergy, String surgeryHistory) {
        this.patientId = patientId;
        this.name = name;
        this.nik = nik;
        this.allergy = allergy;
        this.surgeryHistory = surgeryHistory;
    }

    public String getName() {
        return name;
    }

    public String getNik() {
        return nik;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getSurgeryHistory() {
        return surgeryHistory;
    }

    @Override
    public PatientProfileV2 copy() {
        return new PatientProfileV2(patientId, name, nik, allergy, surgeryHistory);
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public int getVersion() {
        return 2; // This is version 2 of the patient profile
    }

    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }

    @Override
    public void maskSensitiveData() {
        if (nik != null) {
            nik = "****************"; // Mask the NIK for V2 if doctor's level are lower than 3
        }
        if (allergy != null) {
            allergy = "HIDDEN - SENSITIVE"; // Mask the allergy information for V2 if doctor's level are lower than 3
        }
        if (surgeryHistory != null) {
            surgeryHistory = "HIDDEN - SENSITIVE"; // Mask the surgery history for V2 if doctor's level are lower than 3
        }
    }

    @Override
    public String toString() {
        return "Patient Profile" +
                "\nPatientId: " + patientId +
                "\nName: " + name +
                "\nNIK: " + nik +
                "\nAllergy: " + allergy +
                "\nSurgeryHistory: " + surgeryHistory +
                "\nSecurityLevel: " + securityLevel;
    }
    
}
