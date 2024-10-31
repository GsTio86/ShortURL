# URL 短網址服務

**ShortURL** 是使用 **Spring Boot** 構建的短網址服務後端，負責處理建立、管理和重新導向短網址，搭配前端為使用者提供快速的短網址服務。

此專案實現了前後端分離，以增強模組化和可維護性，確保系統具有擴展性且易於維護。

---

## **專案功能**
- **創建短網址**：為提供的長網址生成唯一的短網址。
- **重定向到原網址**：根據短網址將使用者重新導向原本的網址。
- **使用次數**：對每個短網址的使用情況進行追蹤，但這個不會給使用者看，僅管理者可查看。
- **刪除短網址**：必要時可刪除短網址，但僅限管理者可刪除，此部分需要驗證。
- **後端 API**：提供 RESTful API 用於創建和管理短網址，適合與各種前端或客戶端應用程式整合。

---

## **使用技術**
- **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)**：專案使用的 Java 版本。
- **[Spring Boot](https://spring.io/projects/spring-boot)**：作為主要框架，用於建立 RESTful API。
- **[MyBatis](https://mybatis.org/mybatis-3/)**：負責 ORM（物件關聯映射），簡化資料庫操作。
- **[Redis](https://redis.io/)**：用於緩存短網址，避免多次查詢資料庫。
- **[MurmurHash3](https://github.com/eprst/murmur3)**：用於生成短網址的Hash值，以確保其唯一性和效率。
- **[Lombok](https://projectlombok.org/)**：用於簡化Java程式。
- **[Swagger UI](https://swagger.io/tools/swagger-ui/)**：提供 API 文件及互動測試平台。
- **[Logback](https://logback.qos.ch/)**：用於設定輸出日誌並存到logs，用於查看日誌內容。
- **[Thymeleaf](https://www.thymeleaf.org/)**：作為短網址的回應模板。
- **[Junit 5](https://junit.org/junit5/)**：單元測試。
- **[spring-dotenv](https://github.com/paulschwarz/spring-dotenv)**：管理 Spring Boot 環境變數。


---

### **克隆專案**
要開始使用，從 Git 克隆此專案：
```sh
git clone https://github.com/GsTio86/ShortURL.git -b shorturl-backend
