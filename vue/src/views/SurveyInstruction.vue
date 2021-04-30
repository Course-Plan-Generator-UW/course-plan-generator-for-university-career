<template>
<div>
    <el-header>
      <NavBar/>
    </el-header>
  <div id="box">
        <div id="text-box">
            <h1>Welcome to Course Generator!</h1>
            <p>Pick your major to begin</p>
            <el-select
                  v-model="major"
                  placeholder="Choose a major"
                >
                  <el-option
                    v-for="item in majors"
                    :key="item"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
        </div>
        <div id="btn-box">
            <el-button type="primary" @click="jump">Start</el-button>
        </div>
    </div>
</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
    name: "SurveyInstruction",
  components: {
    NavBar
  },
  methods: {
    jump(){
        this.$router.push("/Survey?q=1")
    }
  },
  data(){
      return{
          majors:["computer science"],
          major: ""
      }
  }
}
</script>

<style>
html, body{
    margin: 0px;
    height: 100%;
}
#box{
    border: 1px solid black;
    margin: 10% 20% 10% 20%;
    text-align: center;
    height: 60%
}
#text-box{
    width: 100%;
    height: 85%;
    overflow:scroll
}
#btn-box{
    width: 100%;
    height:15%
}
#link{
    color: black;
    text-decoration: none;
    bottom: 0
}
</style>