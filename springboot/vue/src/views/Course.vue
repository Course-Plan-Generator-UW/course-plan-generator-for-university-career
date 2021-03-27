<template>
  <el-container>
      <el-header>
        <NavBar />
      </el-header>
      <h2>Computer Science 506</h2>
      <h3>Software Enginnering</h3>
      <p>Ideas and techniques for designing, developing, and modifying large software systems. Topics include software engineering processes; requirements and specifications; project team organization and management; software architectures; design patterns; testing and debugging; and cost and quality metrics and estimation. Students will work in large teams on a substantial programming project. Enroll Info: None</p>
  <div>
      <p>Current Rate:</p>
      <el-rate
  v-model="curRate"
  disabled
  show-score
  text-color="#ff9900"
  score-template="{value}">
</el-rate>
  </div>
  <div>
      <p>Rate this course:</p>
      <el-rate
  v-model="userRate"
  show-text
  :texts="['Poor','Unsatisfied','Average','Satisfied','Amazing']">
</el-rate>
  </div>
  </el-container>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
  name: "Course",
  components: {
    NavBar
  },
  methods: {
    
  },
  data(){
      return {
          curRate: 4.2,
          userRate: null
      }
  }
}
</script>

<style>

</style>