<template>
  <div class="body">
    <div class="login-form">
      <div>
        <h1>Welcome to course generator!</h1>
        <h3>Start your tour by signing up!</h3>
      </div>
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="login"
      >
        <el-form-item label="Username" prop="userName">
          <el-input v-model="ruleForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="userPwd">
          <el-input type="password" v-model="ruleForm.userPwd"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="First Name" prop="firstName">
          <el-input v-model="ruleForm.firstName"></el-input>
        </el-form-item>
        <el-form-item label="Last Name" prop="lastName">
          <el-input v-model="ruleForm.lastName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >Sign Up</el-button
          >
          <el-button @click="resetForm('ruleForm')">Reset</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        userName: "",
        userPwd: "",
        email: "",
        firstName: "",
        lastName: "",
        studentIdentity: "student",
      },
      rules: {
        userName: [
          {
            required: true,
            message: "Please enter your username",
            trigger: "blur",
          },
        ],
        userPwd: [
          {
            required: true,
            message: "Please enter your password",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            message: "Please enter your email",
            trigger: "blur",
          },
        ],
        firstName: [
          {
            required: true,
            message: "Please enter your first name",
            trigger: "blur",
          },
        ],
        lastName: [
          {
            required: true,
            message: "Please enter your last name",
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
            text: "Signing you up...",
            spinner: "el-icon-loading",
            background: "rgba(0, 0, 0, 0.7)",
          });
          const _this = this;
          this.$axios
            .post(
              "http://127.0.0.1:8081/scc/user/regist",
              this
                .ruleForm /* , {
                  headers:{
                      "Authorization" : "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ0b2tlblR5cGUiOiJhY2Nlc3NUb2tlbiIsImV4cCI6MTYxNjM3MDQyNX0.7PuZ1Uu42OfpiR3-vUG9cXtaZMeNIiR2Ymv5TLnUMkM"
                  }
              } */
            )
            .then((res) => {
              loading.close();
              const data = res.data;
              if (data.code === 0) {
                this.$alert("You successfully signed up!", "Congratulations", {
                  confirmButtonText: "Go to log in",
                });
                _this.$router.push("/");
              } else if (data.code === -1) {
                this.$alert("This username has already been used!", "Warning", {
                  confirmButtonText: "Confirm",
                });
              } else {
                this.$alert("Unrecognized error: " + data.msg, "Warning", {
                  confirmButtonText: "Confirm",
                });
              }
              //_this.$store.commit("SET_USERINFO", userInfo);
              //_this.$router.push("/MainMenu");
            });
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
  },
};
</script>

<style>
.login {
  max-width: 500px;
  margin: 0 auto;
}
</style>