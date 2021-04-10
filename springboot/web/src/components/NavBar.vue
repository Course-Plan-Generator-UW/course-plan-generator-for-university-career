<template>
  <!-- <div class="sidebar">
      <a class="a" href="./information.html">Course Information</a>
      <a class="a" href="./personal.html">Personal Information</a>
      <a class="a" href="./plan.html">Generate New Plan</a>
      <a class="a" href="./schedule.html">Check my Schedule</a>
    </div> -->
  <div>
    <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1"
        ><el-button type="primary">Course Search</el-button></el-menu-item
      >
      <el-menu-item index="2"
        ><el-button type="primary">Generate Schedule</el-button></el-menu-item
      >
      <el-menu-item index="3"
        ><el-button type="primary">Check My Schedule</el-button></el-menu-item
      >
      <el-menu-item index="4"
        ><el-button type="text" v-html="welcome"></el-button
      ></el-menu-item>
      <el-menu-item index="5"
        ><el-button type="danger">Log Out</el-button></el-menu-item
      >
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    jumpToCS() {
      this.$router.push("/CourseSearch");
    },
    handleSelect(key, keyPath) {
      switch (key) {
        case "1":
          this.$router.push("/CourseSearch");
          break;
        case "2":
          this.$router.push("/SurveyInstruction");
          break;
        case "3":
          console.log("check");
          break;
        case "5":
          this.logOut();
          break;
        default:
      }
    },
    logOut() {
      this.$axios
        .get("http://www.chqh.tech:8081/user/logout", {
          params: {
            //userId: this.$store.getters.getUserToken.userId,
            userId: JSON.parse(sessionStorage.getItem("userToken")).userId
          },
          headers: {
            //Authorization: this.$store.getters.getUserToken.accessToken,
            Authorization: JSON.parse(sessionStorage.getItem("userToken")).accessToken
          },
        })
        .then((response) => {
          if (response.data.code === 0) {
            this.$notify({
              title: "Success",
              message: "You have logged out",
              type: "success",
            });
            sessionStorage.clear()
            this.$router.push("/")
          }else{
            this.$notify({
              title: "Warning",
              message: "Log out failed",
              type: "warning",
            });
          }
        });
    },
  },
  data() {
    return {
      welcome: "",
    };
  },
  created() {
    if (sessionStorage.getItem("userInfo")) {
      //this.$store.commit("SET_USERTOKEN", sessionStorage.getItem(userInfo))
      this.$store.commit(
        "SET_USERINFO",
        JSON.parse(sessionStorage.getItem("userInfo"))
      );
      this.welcome = "Hi, " + this.$store.getters.getUserInfo.username + "!";
    }else{
      this.$notify({
          title: 'Warning',
          message: 'Please log in to get access to course search',
          type: 'warning'
        });
        this.$router.push("/")
    }
  },
};
</script>

<style>
.sidebar {
  box-sizing: border-box;
  height: 100%;
  width: 380px;
  border-right: 2px solid black;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
}
.a {
  text-align: center;
  color: black;
  border: 1px solid black;
  cursor: pointer;
  font-size: 24px;
  font-weight: 600;
  margin: 0 40px;
  padding: 25px 0;
  border-radius: 6px;
  margin-bottom: 30px;
  white-space: nowrap;
  width: 100%;
}
</style>