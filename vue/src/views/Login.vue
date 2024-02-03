<template>
  <!-- 整体背景 -->
  <div class="login-wrap">
    <!--输入框-->
    <div class="form-wrapper">
      <div class="header">
        慧眼 智慧工厂管理系统
      </div>
      <div class="input-wrapper">
        <div class="border-wrapper">
          <input type="text" name="username" placeholder="用户名" class="border-item" autocomplete="off" v-model="user.username" />
        </div>
        <div class="border-wrapper">
          <input type="password" name="password" placeholder="密码" class="border-item" autocomplete="off"  v-model="user.password"/>
        </div>
      </div>
      <div class="action">
        <div class="btn" @click="login">登录</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {},
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      this.request.post("http://localhost:9090/user/login", this.user).then(res => {
        if(!res) {
          this.$message.error("用户名或密码错误")
        } else {
            this.user=res
            this.$router.push({ path: "/home", query: { user: JSON.stringify(this.user) }})}
      })
    }
  }
};
</script>
<style scoped>
.login-wrap {
  height: 100%;
  font-family: JetBrains Mono Medium;
  display: flex;
  align-items: center;
  justify-content: center;
  /* background-color: #0e92b3; */
  background: url('../assets/背景.png');
  width: 100%;
  height: 100%;
  position: absolute;
}

.form-wrapper {
  width: 400px;
  background-color: rgba(41, 45, 62, 0.4);
  color: #fff;
  border-radius: 2px;
  padding: 100px;
}

.form-wrapper .header {
  text-align: center;
  font-size: 30px;
  text-transform: uppercase;
  line-height: 100px;
}

.form-wrapper .input-wrapper input {
  background-color: rgb(41, 45, 62);
  border: 0;
  width: 100%;
  text-align: center;
  font-size: 15px;
  color: #fff;
  outline: none;
}

.form-wrapper .input-wrapper input::placeholder {
  text-transform: uppercase;
}

.form-wrapper .input-wrapper .border-wrapper {
  background-image: linear-gradient(to right, #e8198b, #0eb4dd);
  width: 100%;
  height: 50px;
  margin-bottom: 20px;
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.form-wrapper .input-wrapper .border-wrapper .border-item {
  height: calc(100% - 4px);
  width: calc(100% - 4px);
  border-radius: 30px;
}

.form-wrapper .action {
  display: flex;
  justify-content: center;
}

.form-wrapper .action .btn {
  width: 60%;
  text-transform: uppercase;
  border: 2px solid #0e92b3;
  text-align: center;
  line-height: 50px;
  border-radius: 30px;
  cursor: pointer;
}

.form-wrapper .action .btn:hover {
  background-image: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);
}

.form-wrapper .icon-wrapper {
  text-align: center;
  width: 60%;
  margin: 0 auto;
  margin-top: 20px;
  border-top: 1px dashed rgb(146, 146, 146);
  padding: 20px;
}

.form-wrapper .icon-wrapper i {
  font-size: 20px;
  color: rgb(187, 187, 187);
  cursor: pointer;
  border: 1px solid #fff;
  padding: 5px;
  border-radius: 20px;
}

.form-wrapper .icon-wrapper i:hover {
  background-color: #000202;
}
</style>
