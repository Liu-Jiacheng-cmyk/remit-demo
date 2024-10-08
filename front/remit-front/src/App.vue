<!-- src/App.vue -->
<template>
  <div id="app">
    <!-- 应用 Element Plus 提供的整体布局样式 -->
    <el-container style="min-height: 100vh;">
      <el-header>
        <el-row justify="space-between" type="flex">
          <el-col :span="4">
            <h1 class="logo">Remit Demo</h1>
          </el-col>
          <el-col :span="4" v-if="isAuthenticated">
            <el-button type="danger" @click="logout" size="small">Logout</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <!-- 渲染路由组件 -->
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { useStore } from 'vuex';

export default {
  name: 'App',
  setup() {
    const store = useStore();

    const logout = () => {
      store.dispatch('logout');
    };

    return {
      isAuthenticated: store.state.isAuthenticated,
      logout,
    };
  },
};
</script>

<style scoped>
/* 应用基础样式，增加视觉一致性 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.logo {
  color: #42b983;
  font-weight: bold;
}
</style>
