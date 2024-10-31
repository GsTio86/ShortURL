<template>
  <ul class="list-group mt-5">
    <div class="input-group input-group-lg mb-3">
      <input type="url" v-model="longUrl" class="form-control" placeholder="輸入要縮短的網址">
      <button class="btn btn-primary" type="button" @click="tryShortUrl()">縮址</button>
    </div>
    <ResultUrl v-if="shortUrl" :long-url="longUrl" :short-url="shortUrl"/>
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
      shortUrl: ''
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
.input-group {
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
  justify-content: center;
}

.input-group .form-control {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}

.input-group .btn {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  white-space: nowrap;
}

@media (min-width: 768px) {
  .input-group-lg .form-control {
    width: 75%;
  }
}

@media (max-width: 576px) {
  .input-group {
    width: 100%;
    justify-content: flex-start;
  }
  .input-group .form-control,
  .input-group .btn {
    flex: 1;
    max-width: 50%;
    border-radius: 0;
  }
}

</style>