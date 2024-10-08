<template>
    <div class="sign-container">
      <el-card class="sign-card">
        <h2>登录 - 模拟插卡</h2>
        <el-form :model="loginForm" ref="loginForm" label-width="100px">
          <el-form-item label="Card Number">
            <!-- 使用 el-select 代替普通的输入框 -->
            <el-select
              v-model="loginForm.cardNumber"
              placeholder="请选择卡号"
              filterable
              @focus="fetchStoredCardNumbers"
            >
              <el-option
                v-for="card in storedCardNumbers"
                :key="card"
                :label="card"
                :value="card"
              ></el-option>
            </el-select>
          </el-form-item>
  
          <el-form-item label="Password">
            <el-input
              type="password"
              v-model="loginForm.password"
              autocomplete="off"
            />
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="handleLogin">确认</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  
  export default {
    name: "Sign",
    setup() {
      const router = useRouter();
      const loginForm = ref({
        cardNumber: "",
        password: "",
      });
  
      const storedCardNumbers = ref([]);
  
      // 获取缓存中的卡号
      const fetchStoredCardNumbers = () => {
        const storedCards = localStorage.getItem("storedCardNumbers");
        if (storedCards) {
          storedCardNumbers.value = JSON.parse(storedCards);
        }
      };
  
      // 登录处理函数
      const handleLogin = () => {
        // 这里可以进行简单校验
        if (!loginForm.value.cardNumber || !loginForm.value.password) {
          return alert("请填写卡号和密码");
        }
        
        // 将卡号存入 localStorage 缓存中
        const cardNumbers = localStorage.getItem("storedCardNumbers");
        let cardArray = cardNumbers ? JSON.parse(cardNumbers) : [];
        if (!cardArray.includes(loginForm.value.cardNumber)) {
          cardArray.push(loginForm.value.cardNumber);
          localStorage.setItem("storedCardNumbers", JSON.stringify(cardArray));
        }
  
        // 假设通过校验，跳转到导航页面
        router.push("/dashboard");
      };
  
      return {
        loginForm,
        storedCardNumbers,
        fetchStoredCardNumbers,
        handleLogin,
      };
    },
  };
  </script>
  
  <style scoped>
.sign-container {
  position: absolute;
  top: 150px; /* 控制距离顶部的距离 */
  left: 150px; /* 控制距离左边的距离 */
}
  
  .sign-card {
    width: 400px;
    padding: 20px;
  }
  </style>
  