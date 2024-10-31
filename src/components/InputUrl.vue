<template>
  <ul class="list-group mt-5">
    <div class="input-group input-group-lg mb-3">
      <input type="url" v-model="longUrl" class="form-control w-75" placeholder="輸入要縮短的網址">
      <button class="btn btn-primary" type="button" @click="tryShortUrl()">縮址</button>
    </div>
    <ResultUrl v-if="shortUrl" :long-url="longUrl" :short-url="shortUrl" :qr-code="qrCode" />
  </ul>
</template>
<script>
import axios from 'axios';
import swal from 'sweetalert2';
import ResultUrl from '@/components/ResultUrl.vue';

export default {
  name: 'InputUrl',
  components: { ResultUrl },
  data() {
    return {
      longUrl: '',
      shortUrl: '',
      qrCode: '',
      qrCodeSize: 150
    };
  },
  methods: {
    tryShortUrl() {
      swal.fire({
        title: '縮址中...',
        text: '請稍候',
        allowOutsideClick: false,
        didOpen: () => {
          swal.showLoading();
        }
      })

      axios.post(`${import.meta.env.VITE_API_URL}/generate`, {url: this.longUrl}, {
            headers: {
              'Content-Type': 'application/json'
            }
          }
      ).then((res) => {
        this.shortUrl = window.location.origin + '/' + res.data
        this.qrCode = `https://api.qrserver.com/v1/create-qr-code/?size=${this.qrCodeSize}x${this.qrCodeSize}&data=${this.shortUrl}`
        swal.fire({
          title: '縮址成功',
          text: '短網址已產生，請查看下方結果。',
          icon: 'success'
        });
      }).catch((err) => {
        swal.fire({
          title: '縮址失敗',
          text: `請確認網址是否正確! ${err.message}`,
          icon: 'error'
        });
      });
    }
  }
}
</script>
<style>
/* 確保輸入框和按鈕在所有螢幕上保持水平排列 */
.input-group {
  flex-wrap: nowrap; /* 禁止自動換行 */
}

.input-group .form-control {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}

.input-group .btn {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  white-space: nowrap; /* 防止按鈕內文字換行 */
}

/* 在小螢幕上確保輸入框和按鈕都適應畫面寬度 */
@media (max-width: 576px) {
  .input-group {
    width: 100%;
  }

  .input-group .form-control {
    flex: 1;
  }

  .input-group .btn {
    flex: 0; /* 讓按鈕不會撐滿整列 */
  }
}

</style>