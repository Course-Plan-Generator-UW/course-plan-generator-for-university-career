<template>
  <div class="common-layout">
    <!-- <link rel="stylesheet" href="./main.css" /> -->
    <el-container>
      <el-header>
        <NavBar />
      </el-header>
      <!-- <el-main> -->
      <div style="text-align: center; margin: 150px 0 0 0">
        <h1 class="list-text">Welcome!</h1>
        <div id="list1">
          <a @click="jumpCS" class="list-text">Course Search</a>
        </div>
        <div id="list2">
          <a @click="jumpGS" class="list-text">Generate Schedule</a>
        </div>
        <div id="list3">
          <p style="display: inline; font-size: 60px">Check my Schedule</p>
        </div>
      </div>
      <!-- <button @click="test">HERE</button> -->
      <!-- </el-main> -->
    </el-container>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "MainMenu",
  components: {
    NavBar,
  },
  methods: {
    jumpCS() {
      this.$router.push("/CourseSearch");
    },
    jumpGS() {
      this.$router.push("/SurveyInstruction");
    },
    test(){
      console.log(this.$store.getters.getUserInfo)
    },
  }
};
</script>

<style>
/* .el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
} */
html,
body {
  margin: 0px;
  height: 100%;
}
img {
  vertical-align: middle;
  float: left;
}
h1 {
  display: inline-block;
  vertical-align: middle;
}
#list1 {
  margin: 0 650px 0 650px;
  border-style: solid;
  vertical-align: middle;
}
#list2 {
  margin: 50px 650px 50px 650px;
  border-style: solid;
}
#list3 {
  margin: 0 650px 0 650px;
  border-style: solid;
}
.list-text {
  color: black;
  text-decoration: none;
  font-size: 60px;
}
</style>