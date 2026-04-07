public class MainRun {
    public static void main(String[] args) {

        // Data V1
        PatientProfileV1 v1 = new PatientProfileV1("P001", "Phainon", "123456");

        // Data V2
        PatientProfileV2 v2 = new PatientProfileV2("P002", "Mydei", "654321", "Peanut", "Appendectomy");

        // Gateway
        IntegrationGateway<PatientProfileV1> gatewayV1 = new IntegrationGateway<>(v1);
        IntegrationGateway<PatientProfileV2> gatewayV2 = new IntegrationGateway<>(v2);

        // Dokter akses rendah
        System.out.println("\n=== LOW ACCESS ===");
        gatewayV1.fetchData("P001", 1).display();
        gatewayV2.fetchData("P002", 1).display();

        // Dokter akses tinggi
        System.out.println("\n=== HIGH ACCESS ===");
        gatewayV1.fetchData("P001", 3).display();
        gatewayV2.fetchData("P002", 3).display();
    }
}
