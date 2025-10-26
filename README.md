# Veteriner Yönetim Sistemi

Bu proje, bir veteriner kliniğinin işlerini yönetebilmesi için tasarlanmış bir API'dir.

## UML Diyagramı

[UML Diyagramı Buraya Eklenecek]

## API Dokümantasyonu

[API Dokümantasyonu Buraya Eklenecek]

## Postman Koleksiyonu

[Postman Koleksiyonu Buraya Eklenecek]

## Kurulum ve Çalıştırma

Projenin bağımlılıklarını yüklemek ve çalıştırmak için aşağıdaki komutları kullanabilirsiniz:

```bash
mvn clean install
mvn spring-boot:run
```

veya Maven Wrapper kullanarak:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Veritabanı Ayarları (H2)

Uygulama varsayılan olarak H2 veritabanı ile çalışmaktadır. H2 konsoluna erişmek için:

`http://localhost:8080/h2-console` adresini kullanabilirsiniz. JDBC URL olarak `jdbc:h2:mem:veteriner` ve kullanıcı adı olarak `sa` giriniz.

## Veritabanı Ayarları (PostgreSQL)

PostgreSQL kullanmak için `application.properties` dosyasındaki H2 ayarlarını yorum satırı yapıp aşağıdaki ayarları ekleyebilirsiniz:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/veteriner_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Proje Katmanları

- **entities**: Veritabanı tablolarını temsil eden JPA varlıkları.
- **dao**: Veritabanı işlemleri için Repository arayüzleri.
- **dto**: API istek ve yanıtları için veri transfer nesneleri.
- **business**: İş mantığını içeren servis katmanı.
- **api**: RESTful API endpoint'lerini içeren Controller katmanı.
- **core**: Ortak yardımcı sınıflar, istisna yönetimi ve model mapper yapılandırması.
