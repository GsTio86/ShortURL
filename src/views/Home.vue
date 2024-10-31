<template>
  <ThemeSwitch />
  <InputUrl />
</template>
<script>
import InputUrl from '@/components/InputUrl.vue';
import ThemeSwitch from "@/components/ThemeSwitch.vue";

export default {
  name: 'Home',
  components: {
    ThemeSwitch,
    InputUrl,
  },
}
</script>
<style>

</style>
