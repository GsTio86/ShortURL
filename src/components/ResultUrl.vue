<template>
  <div class="row card mt-5">
    <div class="card-header">縮址結果</div>
    <div class="card-body">
      <div class="row align-items-center">
        <div class="col-12 col-md-8 mb-3">
          <p class="h5 mb-2 no-wrap">
            原網址：<a :href="longUrl" target="_blank">{{ longUrl }}</a><br /><br />
            短網址：<a :href="shortUrl" target="_blank">{{ shortUrl }}</a>
          </p>
        </div>
        <div id="qrcode" class="col-12 col-md-4 d-flex justify-content-center mb-3">
          <qrcode-vue :value="shortUrl" :size="150" level="L" :margin="1" class="img-fluid rounded" />
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <button
              class="btn btn-secondary w-100"
              @click="copyShortUrl"
          >
            複製短網址
          </button>
        </div>
        <div class="col-6">
          <button
              class="btn btn-success w-100"
              @click="downloadQrCode"
          >
            下載 QR Code
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import swal from "sweetalert2";
  import QrcodeVue from 'qrcode.vue'

  export default {
    name: 'ResultUrl',
    props: ['longUrl', 'shortUrl', 'qrCode'],
    methods: {
      copyShortUrl() {
        navigator.clipboard.writeText(this.shortUrl).then(() => {
          swal.fire({
            title: '已複製短網址',
            text: this.shortUrl,
            icon: 'success'
          });
        }).catch((err) => {
          swal.fire({
            title: '複製失敗',
            text: '無法複製短網址',
            icon: 'error'
          });
        });
      },
      downloadQrCode() {
        let canvas = document.getElementById('qrcode').getElementsByTagName('canvas');
        let link = document.createElement('a');
        link.href = canvas[0].toDataURL('img/png')
        link.download = 'qrcode.png';
        link.click();
        link.remove();
      }
    },
    components: {
      QrcodeVue,
    }
  }
</script>
<style>
.no-wrap {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>