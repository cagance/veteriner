# Veteriner Yönetim Sistemi API Dokümantasyonu

Bu doküman, Veteriner Yönetim Sistemi API'sinin mevcut uç noktalarını ve kullanımını açıklamaktadır.

## Temel URL

`http://localhost:8080/v1`

## Müşteri (Customer) API

### Müşteri Listele
- **GET** `/v1/customers`
- **Açıklama**: Tüm müşterileri listeler.
- **Yanıt**: `List<CustomerResponse>`

### Müşteri ID ile Getir
- **GET** `/v1/customers/{id}`
- **Açıklama**: Belirtilen ID'ye sahip müşteriyi getirir.
- **Yanıt**: `CustomerResponse`

### Müşteri İsme Göre Filtrele
- **GET** `/v1/customers/filter?name={name}`
- **Açıklama**: Belirtilen isme sahip müşteriyi getirir.
- **Yanıt**: `CustomerResponse`

### Yeni Müşteri Ekle
- **POST** `/v1/customers`
- **Açıklama**: Yeni bir müşteri ekler.
- **İstek Gövdesi**: `CustomerSaveRequest`
- **Yanıt**: `CustomerResponse`

### Müşteri Güncelle
- **PUT** `/v1/customers`
- **Açıklama**: Mevcut bir müşteriyi günceller.
- **İstek Gövdesi**: `CustomerUpdateRequest`
- **Yanıt**: `CustomerResponse`

### Müşteri Sil
- **DELETE** `/v1/customers/{id}`
- **Açıklama**: Belirtilen ID'ye sahip müşteriyi siler.
- **Yanıt**: `Result`

## Hayvan (Animal) API

### Hayvan Listele
- **GET** `/v1/animals`
- **Açıklama**: Tüm hayvanları listeler.
- **Yanıt**: `List<AnimalResponse>`

### Hayvan ID ile Getir
- **GET** `/v1/animals/{id}`
- **Açıklama**: Belirtilen ID'ye sahip hayvanı getirir.
- **Yanıt**: `AnimalResponse`

### Hayvan İsme Göre Filtrele
- **GET** `/v1/animals/filter-by-name?name={name}`
- **Açıklama**: Belirtilen isme sahip hayvanları listeler.
- **Yanıt**: `List<AnimalResponse>`

### Hayvan Müşteriye Göre Filtrele
- **GET** `/v1/animals/filter-by-customer?customerId={customerId}`
- **Açıklama**: Belirtilen müşteri ID'sine sahip hayvanları listeler.
- **Yanıt**: `List<AnimalResponse>`

### Yeni Hayvan Ekle
- **POST** `/v1/animals`
- **Açıklama**: Yeni bir hayvan ekler.
- **İstek Gövdesi**: `AnimalSaveRequest`
- **Yanıt**: `AnimalResponse`

### Hayvan Güncelle
- **PUT** `/v1/animals`
- **Açıklama**: Mevcut bir hayvanı günceller.
- **İstek Gövdesi**: `AnimalUpdateRequest`
- **Yanıt**: `AnimalResponse`

### Hayvan Sil
- **DELETE** `/v1/animals/{id}`
- **Açıklama**: Belirtilen ID'ye sahip hayvanı siler.
- **Yanıt**: `Result`

## Doktor (Doctor) API

### Doktor Listele
- **GET** `/v1/doctors`
- **Açıklama**: Tüm doktorları listeler.
- **Yanıt**: `List<DoctorResponse>`

### Doktor ID ile Getir
- **GET** `/v1/doctors/{id}`
- **Açıklama**: Belirtilen ID'ye sahip doktoru getirir.
- **Yanıt**: `DoctorResponse`

### Yeni Doktor Ekle
- **POST** `/v1/doctors`
- **Açıklama**: Yeni bir doktor ekler.
- **İstek Gövdesi**: `DoctorSaveRequest`
- **Yanıt**: `DoctorResponse`

### Doktor Güncelle
- **PUT** `/v1/doctors`
- **Açıklama**: Mevcut bir doktoru günceller.
- **İstek Gövdesi**: `DoctorUpdateRequest`
- **Yanıt**: `DoctorResponse`

### Doktor Sil
- **DELETE** `/v1/doctors/{id}`
- **Açıklama**: Belirtilen ID'ye sahip doktoru siler.
- **Yanıt**: `Result`

## Müsait Gün (AvailableDate) API

### Müsait Gün Listele
- **GET** `/v1/available-dates`
- **Açıklama**: Tüm müsait günleri listeler.
- **Yanıt**: `List<AvailableDateResponse>`

### Müsait Gün ID ile Getir
- **GET** `/v1/available-dates/{id}`
- **Açıklama**: Belirtilen ID'ye sahip müsait günü getirir.
- **Yanıt**: `AvailableDateResponse`

### Yeni Müsait Gün Ekle
- **POST** `/v1/available-dates`
- **Açıklama**: Yeni bir müsait gün ekler.
- **İstek Gövdesi**: `AvailableDateSaveRequest`
- **Yanıt**: `AvailableDateResponse`

### Müsait Gün Güncelle
- **PUT** `/v1/available-dates`
- **Açıklama**: Mevcut bir müsait günü günceller.
- **İstek Gövdesi**: `AvailableDateUpdateRequest`
- **Yanıt**: `AvailableDateResponse`

### Müsait Gün Sil
- **DELETE** `/v1/available-dates/{id}`
- **Açıklama**: Belirtilen ID'ye sahip müsait günü siler.
- **Yanıt**: `Result`

## Aşı (Vaccine) API

### Aşı Listele
- **GET** `/v1/vaccines`
- **Açıklama**: Tüm aşıları listeler.
- **Yanıt**: `List<VaccineResponse>`

### Aşı ID ile Getir
- **GET** `/v1/vaccines/{id}`
- **Açıklama**: Belirtilen ID'ye sahip aşıyı getirir.
- **Yanıt**: `VaccineResponse`

### Hayvan ID'sine Göre Aşıları Filtrele
- **GET** `/v1/vaccines/filter-by-animal?animalId={animalId}`
- **Açıklama**: Belirtilen hayvan ID'sine sahip aşıları listeler.
- **Yanıt**: `List<VaccineResponse>`

### Koruma Bitiş Tarihine Göre Aşıları Filtrele
- **GET** `/v1/vaccines/filter-by-date?startDate={startDate}&endDate={endDate}`
- **Açıklama**: Belirtilen tarih aralığında koruma bitiş tarihi olan aşıları listeler.
- **Yanıt**: `List<VaccineResponse>`

### Yeni Aşı Ekle
- **POST** `/v1/vaccines`
- **Açıklama**: Yeni bir aşı ekler.
- **İstek Gövdesi**: `VaccineSaveRequest`
- **Yanıt**: `VaccineResponse`

### Aşı Güncelle
- **PUT** `/v1/vaccines`
- **Açıklama**: Mevcut bir aşıyı günceller.
- **İstek Gövdesi**: `VaccineUpdateRequest`
- **Yanıt**: `VaccineResponse`

### Aşı Sil
- **DELETE** `/v1/vaccines/{id}`
- **Açıklama**: Belirtilen ID'ye sahip aşıyı siler.
- **Yanıt**: `Result`

## Randevu (Appointment) API

### Randevu Listele
- **GET** `/v1/appointments`
- **Açıklama**: Tüm randevuları listeler.
- **Yanıt**: `List<AppointmentResponse>`

### Randevu ID ile Getir
- **GET** `/v1/appointments/{id}`
- **Açıklama**: Belirtilen ID'ye sahip randevuyu getirir.
- **Yanıt**: `AppointmentResponse`

### Doktor ID ve Tarih Aralığına Göre Randevuları Filtrele
- **GET** `/v1/appointments/filter-by-doctor?doctorId={doctorId}&appointmentDate={appointmentDate}`
- **Açıklama**: Belirtilen doktor ID'sine ve tarih aralığına sahip randevuları listeler.
- **Yanıt**: `List<AppointmentResponse>`

### Hayvan ID ve Tarih Aralığına Göre Randevuları Filtrele
- **GET** `/v1/appointments/filter-by-animal?animalId={animalId}&appointmentDate={appointmentDate}`
- **Açıklama**: Belirtilen hayvan ID'sine ve tarih aralığına sahip randevuları listeler.
- **Yanıt**: `List<AppointmentResponse>`

### Yeni Randevu Ekle
- **POST** `/v1/appointments`
- **Açıklama**: Yeni bir randevu ekler.
- **İstek Gövdesi**: `AppointmentSaveRequest`
- **Yanıt**: `AppointmentResponse`

### Randevu Güncelle
- **PUT** `/v1/appointments`
- **Açıklama**: Mevcut bir randevuyu günceller.
- **İstek Gövdesi**: `AppointmentUpdateRequest`
- **Yanıt**: `AppointmentResponse`

### Randevu Sil
- **DELETE** `/v1/appointments/{id}`
- **Açıklama**: Belirtilen ID'ye sahip randevuyu siler.
- **Yanıt**: `Result`
