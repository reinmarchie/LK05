# LK05

## Interfaces

### MedicalRecord.java

Interface sebagai kontrak atribut dasar dari class PatientProfile. berisi getPatientId(), yaitu getter dari atribut patientId. Fungsinya untuk mewajibkan objek dari interface memiliki atribut patientId sebagai identifier objek dengan value unik yang dapat diminta dari sisi front-end.

### Versioned.java

Interface sebagai kontrak untuk versi data dari class PatientProfile. Berisi getVersion(), yaitu getter dari atribut version. Bertujuan agar perbaruan struktur data dapat terpantau (contoh dari V1 ke V2).

### Confidential.java

Interface sebagai kontrak untuk fitur-fitur keamanan data, diantaranya adalah int getSecurityLevel() yang merupkaan fungsi getter untuk atribut securityLevel dan void makeSensitiveData() yang menyembunyikan nilai dari data-data sensitif objek.

### CopyData.java

Sebuah generic interface sebagai kontrak untuk bagi suatu class untuk memungkinkan objek dari class tersebut di-copy ke objek lain.

## MainRun.java

### 1. Inisialisasi Data

Program diawali dengan melakukan instansiasi dua objek pasien "v1" dan "v2" masing-masing dari class PateintProfileV1 dan PatientProfileV2. Kedua objek tersebut merepresentasikan sebuah contoh data pasien dalam suatu database yang kemudian akan diolah di back-end sebelum dikirim ke API, dengan "v1" sebagai data pasien yang dikirim dengan struktur data lama dan "v2" dengan struktur data baru.

### 2. Inisialisasi Gateway

Program melakukan instansiasi objek "gatewayV1" dan "gatewayV2" dari class IntegrationGateway dengan memasukkan objek pasien sebelumnya sebagai argumen untuk masing-masing konstruktor. Tahap ini berperan sebagai simulasi objek pasien yang sudah diambil dari database untuk dioperasikan dengan logika bisnis yang diperlukan.

### 3. Simulasi Request & Response

Program memanggil method fetchData("P001", 1) dengan argumen yang merepresentasikan ID pasien dan level akses dokter, dilanjut dengan fetchData("P002", 1) dari instansi objek IntegrationGateway sebelumnya. Method tersebut mengembalikan objek SecureResponse yang kemudian dipanggil method display() dari objek tersebut. Tahap ini berperan sebagai simulasi pengiriman respon yang berisi data pasien dari permintaan yang dikirim dari sisi front end sebagai dokter dengan akses level rendah. Dilanjut dengan proses yang sama seperti sebelumnya, namun dengan argumen level akses 3 sebagai simulasi permintaan dari dokter dengan level akses tinggi.

## Class

### IntegrationGateway.java

class generic yang bertindak sebagai lapisan keamanan untuk memproses berbagai versi profil pasien dengan membatasi parameter tipe T hanya pada objek yang memenuhi kontrak interface tertentu. Alur utamanya terletak pada metode fetchData, di mana sistem melakukan verifikasi ID pasien dan membandingkan tingkat izin (clearance level) pengguna dengan level keamanan data; jika izin pengguna lebih rendah, sistem akan memanfaatkan fitur cloning untuk membuat salinan data lalu menjalankan fungsi masking guna menyensor informasi sensitif.

### PatientProfileV1.java

Class yang berisi patientId, name, dan nik, serta memiliki security level 2, class ini berfungsi sebagai cetakan untuk membuat sebuah objek PatientProfile atau biodata pasien. serta juga menggunakan sebuah fungsi masking untuk melindungi privasi data pasien

### PatientProfileV2.java

Class yang berisi sama seperti PatientProfileV1 tetapi yang membedakan dengan versi ini adalah penambahana atribut alergy dan surgeryHistory. versi ini adalah versi yang lebih lengkap, security level juga naik yang pada Versi 1 security levelnya 2, di versi ini security levelnya 3 dan juga masking nya tidak hanya dalam NIK tetapi juga menyembunyikan informasi alergi dan riwayat operasi. 

### SecureResponse.java

class generic yang bertindak sebagai kontainer standar untuk membungkus hasil operasi dari gateway, memastikan bahwa tipe data T yang dikirimkan harus memenuhi kontrak MedicalRecord dan Confidential. 
fungsi kode:
- "T extends MedicalRecord & Confidential" : Memastikan hanya objek yang memiliki identitas medis dan fitur kerahasiaan yang bisa dibungkus oleh kelas, 
- "data": Menyimpan objek hasil, 
- "warningMessage": Memberi tahu pengguna jika ada sesuatu yang perlu diperhatikan, 
- "display()": Menghasilkan output konsisten untuk setiap respons yang keluar dari sistem
