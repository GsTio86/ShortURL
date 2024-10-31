# URL 短網址服務

**ShortURL** 的前端部分是使用 **Vue.js** 構建的，旨在為使用者提供快速、直觀的短網址服務體驗。前端與後端實現了分離，確保系統的模組化、可維護性及擴展性。

---

## **專案功能**

- **生成短網址**：使用者可以輸入長網址並生成相應的短網址。
- **重定向到原網址**：通過訪問短網址，使用者將被自動重定向到原始長網址，這裡是搭配部屬提供的Rewrite功能進行實現

---

## **使用技術**

- **[Vue.js](https://vuejs.org/)**：主要的前端框架，用於構建動態且響應式的使用者界面。
- **[Axios](https://github.com/axios/axios)**：用於與後端 API 進行串接。
- **[Vue Router](https://router.vuejs.org/)**：用於管理應用的路徑，使不同的頁面之間能夠方便地切換。
- **[Bootstrap](https://getbootstrap.com/)**：使用 Bootstrap 來設計響應式的使用者介面，提供一致且美觀的佈局和樣式。
- **[SweetAlert2](https://sweetalert2.github.io/)**：用於顯示美觀且易用的提示訊息和彈出框。
- **[QrCode Vue](https://github.com/scopewu/qrcode.vue/)**：根據短網址產生 QR Code，方便使用者掃描訪問短網址。

---

### **克隆專案**

要開始使用，從 Git 克隆此專案：

```sh
git clone https://github.com/GsTio86/ShortURL.git
