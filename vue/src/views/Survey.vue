<template>
  <div>
    <el-header>
      <NavBar />
    </el-header>
    <!-- <div class="body">
      <h2>How much are you interested in the following fields of study?</h2>
      <el-form ref="form" label-width="200px">
        <div
          v-for="(item, index) in subjects"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
          <el-form-item :label="item.label">
            <el-radio v-model="result[index]" label="1"
              >Not Interested</el-radio
            >
            <el-radio v-model="result[index]" label="2"
              >Slightly Interested</el-radio
            >
            <el-radio v-model="result[index]" label="3">Neutral</el-radio>
            <el-radio v-model="result[index]" label="4"
              >Interested</el-radio
            >
            <el-radio v-model="result[index]" label="5"
              >Very Interested</el-radio
            >
          </el-form-item>
        </div>
        <el-form-item>
          <el-button type="primary" @click="submit">Next</el-button>
        </el-form-item>
      </el-form>
    </div> -->
    <h2>My Schedule</h2>
    <div
      style="
        text-align: center;
        height: 50%;
        margin-left: 150px;
        margin-right: 150px;
        width: 50%;
      "
    >
      <el-table
        :data="tableData"
        style="width: 100%"
        empty-text="No result found"
      >
        <el-table-column label="Year" prop="whichYear">
          <template #default="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.whichYear }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Semester">
          <template #default="scope">
            <span style="margin-left: 10px">{{ scope.row.semester }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Course">
          <template #default="scope">
            <span style="margin-left: 10px">{{ scope.row.courseName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Operations">
          <template #default="scope">
            <el-button size="mini" @click="showInfo(scope.$index, scope.row)"
              >Info</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >Delete</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "Survey",
  components: {
    NavBar,
  },
  methods: {
    showInfo(index, row) {
      this.$router.push({
        name: "Course",
        params: { courseName: row.courseName },
      });
    },
    handleDelete(index, row) {
      this.tableData.forEach((item, index) => {
        if (item.courseName == row.courseName) {
          this.tableData.splice(index, 1);
        }
      });
    },
    fillData(){
      this.tableData = [
        { courseName: "Intro to Data Structures", whichYear: "freshman", semester: "fall" },
        { courseName: "General Botany", whichYear: "freshman", semester: "fall" },
        { courseName: "American Literature", whichYear: "freshman", semester: "fall" },
        { courseName: "Modern Algebra", whichYear: "freshman", semester: "fall" },
        { courseName: "British&Anglo Lit 1750-Present", whichYear: "freshman", semester: "spring" },
        { courseName: "Hist of Mass Communication", whichYear: "freshman", semester: "spring" },
        { courseName: "Physical Chemistry", whichYear: "freshman", semester: "spring" },
        { courseName: "Introduction to Programming", whichYear: "freshman", semester: "spring" },
        { courseName: "General Chemistry II", whichYear: "sophomore", semester: "fall" },
        { courseName: "Introduction to Cryptography", whichYear: "sophomore", semester: "fall" },
        { courseName: "Programming I", whichYear: "sophomore", semester: "fall" },
        { courseName: "Intro-Mus Cult of the World", whichYear: "sophomore", semester: "fall" },
        { courseName: "Programming II", whichYear: "sophomore", semester: "spring" },
        { courseName: "The Musical Experience", whichYear: "sophomore", semester: "spring" },
        { courseName: "A Modern Intro to Physics", whichYear: "sophomore", semester: "spring" },
        { courseName: "Intro to Political Theory", whichYear: "sophomore", semester: "spring" },
        { courseName: "Introduction to Algorithms", whichYear: "junior", semester: "fall" },
        { courseName: "Intro-Artificl Intelligence", whichYear: "junior", semester: "fall" },
        { courseName: "Intro to Quantum Mechanics", whichYear: "junior", semester: "fall" },
        { courseName: "Latin America-Introduction", whichYear: "junior", semester: "fall" },
        { courseName: "Data Visualization", whichYear: "junior", semester: "spring" },
        { courseName: "Image Processing", whichYear: "junior", semester: "spring" },
        { courseName: "Intro to Operating Systems", whichYear: "senior", semester: "fall" },
        { courseName: "Database Mgt Systems", whichYear: "senior", semester: "fall" },
        { courseName: "Intro to Computer Networks", whichYear: "senior", semester: "spring" },
        { courseName: "Analysis of Software Artifacts", whichYear: "senior", semester: "spring" },
      ]
    }
  },
  data() {
    return {
      subjects: [
        {
          value: "History",
          label: "History",
        },
        {
          value: "Biology",
          label: "Biology",
        },
        {
          value: "Computer Science",
          label: "Computer Science",
        },
        {
          value: "Literature",
          label: "Literature",
        },
        {
          value: "Physics",
          label: "Physics",
        },
      ],
      result: [],
      tableData: [
      ],
    };
  },
  created(){
    const loading = this.$loading({
      lock: true,
      text: "Loading...",
      spinner: "el-icon-loading",
      background: "rgba(0, 0, 0, 0.7)",
    });
    const _this = this;
    setTimeout(function(){_this.fillData()},2000)
    setTimeout(function(){loading.close()},2000)
  }
};
</script>

<style>
.body {
  text-align: center;
}
</style>