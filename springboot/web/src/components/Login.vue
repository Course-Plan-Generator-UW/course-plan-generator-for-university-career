<template>
  <div class="login-form">
    <h2>Welcome to course generator!</h2>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
    >
      <el-form-item label="Username" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input type="password" v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="jumpSU"
          >Sign Up</el-button
        >
        <el-button type="success" @click="submitForm('ruleForm')"
          >Sign In</el-button
        >
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: "",
        password: "",
        usertype: "student",
        validateCode: "1111",
        validateCodeKey: "111"
      },
      rules: {
        username: [
          {
            required: true,
            message: "Please enter your username",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "Please enter your password",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const loading = this.$loading({
          lock: true,
          text: 'Login you in...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
          const _this = this;
          //alert('submit!');
          this.$axios.post('http://www.chqh.tech:8081/user/login', this.ruleForm).then(res =>{
            loading.close();
              //console.log(res);
              const data = res.data
              if(data.code === 0){
                _this.$store.commit("SET_USERTOKEN", data.data)
                _this.$store.commit("SET_USERINFO", _this.ruleForm)
                _this.$router.push("/MainMenu")
              }else if(data.code === 3){
                this.$alert("Username not exist or incorrect password", "Warning", {
                  confirmButtonText: "Confirm",
                });
              }
              //_this.$router.push("/MainMenu")
            })
          /* const userInfo = {
            username: this.ruleForm.username,
            password: this.ruleForm.password
          };
          console.log(userInfo);
          _this.$store.commit("SET_USERINFO", userInfo)
          if(this.ruleForm.username === 'user'){
            _this.$router.push("/MainMenu")
          }else{
            this.$message.error('Incorrect username or password')
          }
          _this.$router.push("/MainMenu") */
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    jumpSU(){
      this.$router.push("/SignUp")
    }
  },
};
</script>

<style>
.login-form {
  margin: 0 auto;
  top: 50%;
}
</style>