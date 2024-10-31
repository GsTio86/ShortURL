<template>
  <div class="theme-switch">
    <input
        type="checkbox"
        id="darkModeSwitch"
        v-model="isDarkMode"
        @change="toggleTheme"
    />
    <label class="switch" for="darkModeSwitch">
      <div class="toggle">
        <span class="icon sun">☀️</span>
        <span class="icon moon">🌙</span>
      </div>
    </label>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';

export default {
  name: 'ThemeSwitch',
  setup() {
    const isDarkMode = ref(false);

    const toggleTheme = () => {
      const theme = isDarkMode.value ? 'dark' : 'light';
      document.documentElement.setAttribute('data-bs-theme', theme);
      localStorage.setItem('bsTheme', theme);
    };
    onMounted(() => {
      const savedTheme = localStorage.getItem('bsTheme') || 'light';
      isDarkMode.value = savedTheme === 'dark';
      document.documentElement.setAttribute('data-bs-theme', savedTheme);
    });
    return {
      isDarkMode,
      toggleTheme,
    };
  },
};
</script>

<style scoped>
.theme-switch {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}

input[type="checkbox"] {
  display: none;
}

.switch {
  width: 60px;
  height: 30px;
  background-color: #e4e4e4;
  border-radius: 30px;
  position: relative;
  cursor: pointer;
  transition: background-color 0.5s ease;
  display: flex;
  align-items: center;
}

input[type="checkbox"]:checked + .switch {
  background-color: #333;
}

.toggle {
  position: absolute;
  width: 26px;
  height: 26px;
  background-color: white;
  border-radius: 50%;
  left: 2px;
  transition: all 0.5s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

input[type="checkbox"]:checked + .switch .toggle {
  transform: translateX(30px);
  background-color: black;
}

.icon {
  font-size: 18px;
  position: absolute;
  transition: opacity 0.5s ease;
}

.sun {
  color: yellow;
  opacity: 1;
}

.moon {
  color: white;
  opacity: 0;
}

input[type="checkbox"]:checked + .switch .moon {
  opacity: 1;
}

input[type="checkbox"]:checked + .switch .sun {
  opacity: 0;
}

@media (max-width: 576px) {
  .theme-switch {
    top: 15px;
    right: 10px;
    transform: scale(0.8); /* 縮小按鈕以適應手機螢幕 */
  }
}
</style>
