<template>
    <div class="dashboard">
      <el-container>
        <!-- 头部导航栏 -->
        <el-header>
          <el-menu mode="horizontal" :default-active="activeIndex" @select="handleSelect">
            <el-menu-item index="1">账户信息查询</el-menu-item>
            <el-menu-item index="2">余额查询</el-menu-item>
            <el-menu-item index="3">明细查询</el-menu-item>
            <el-menu-item index="4">转账汇款</el-menu-item>
            <!-- 在右侧添加“退卡”按钮 -->
            <el-menu-item class="logout-menu-item" disabled>
              <el-button type="danger" @click="handleLogout">退卡</el-button>
            </el-menu-item>
          </el-menu>
        </el-header>
        <el-main>
          <!-- 路由视图 -->
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Dashboard',
    data() {
      return {
        activeIndex: '1',
      };
    },
    methods: {
      handleSelect(key) {
        // 根据选择的菜单项跳转到相应的页面
        switch (key) {
          case '1':
            this.$router.push({ name: 'AccountInfo' });
            break;
          case '2':
            this.$router.push({ name: 'BalanceInfo' });
            break;
          case '3':
            this.$router.push({ name: 'TransactionHistory' });
            break;
          case '4':
            this.$router.push({ name: 'Transfer' });
            break;
          default:
            break;
        }
      },
      handleLogout() {
        // 清除本地存储中的用户数据
        localStorage.removeItem('user');
        // 跳转回登录页面
        this.$router.push({ name: 'SignIn' });
      },
    },
  };
  </script>
  
  <style scoped>
  .dashboard {
    min-height: 100vh;
  }
  
  .logout-menu-item {
    position: absolute;
    right: 20px;
    top: 10px;
  }
  </style>
  