<template>
  <nav class="navbar navbar-expand-lg mb-4">
    <div class="container-fluid align-items-center">
      <img :src="logo" alt="logo" class="logo d-inline-block align-text-top me-2" />
      <h1 class="title">短網址產生器</h1>
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
.navbar {
  background-color: #2c3e50;
  padding: 0.5rem 1rem;
  border-radius: 8px;
}

.logo {
  height: 80px;
  width: auto;
  max-width: 100%;
}

.title {
  font-size: 1.8rem;
  color: #ecf0f1;
  margin: 0;
  padding-left: 10px;
  text-align: center;
}

@media (max-width: 992px) {
  .logo {
    height: 60px;
  }
  .title {
    font-size: 1.6rem;
  }
}

@media (max-width: 768px) {
  .logo {
    height: 48px;
  }
  .title {
    font-size: 1.4rem;
  }
  .navbar {
    padding: 0.5rem;
  }
}

@media (max-width: 576px) {
  .logo {
    height: 32px;
  }
  .title {
    font-size: 1.2rem;
  }
  .navbar {
    padding: 0.3rem;
  }
}
</style>