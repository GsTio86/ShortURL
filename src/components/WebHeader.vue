<template>
  <nav class="navbar navbar-expand-lg bg-dark mb-4">
    <div class="container-fluid align-items-center">
      <img :src="logo" alt="logo" class="logo d-inline-block align-text-top me-2"/>
      <h1 class="title mt-3 text-light">短網址產生器</h1>
    </div>
  </nav>
</template>
<script>
import logo from '@/assets/logo.png';
export default {
  name: 'WebHeader',
  data() {
    return {
      logo,
    };
  }
};
</script>
<style>
.logo {
  height: 128px;
  width: auto;
  max-width: 100%;
}

@media (max-width: 992px) {
  .logo {
    height: 64px;
  }
}

@media (max-width: 576px) {
  .logo {
    height: 32px;
  }
  .title {
    font-size: 1.5rem;
  }
}
</style>