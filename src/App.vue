<template>
  <div class="container-fluid px-4 mt-5">
    <div class="row justify-content-center">
      <div class="col-12 col-md-8 col-lg-6">
        <WebHeader />
        <router-view />
      </div>
    </div>
  </div>
</template>
<script>
import WebHeader from '@/components/WebHeader.vue';
import ThemeSwitch from "@/components/ThemeSwitch.vue";
import InputUrl from '@/components/InputUrl.vue';

export default {
  name: 'Home',
  components: {
    WebHeader,
    InputUrl,
    ThemeSwitch
  }
}
</script>
<style>

</style>
