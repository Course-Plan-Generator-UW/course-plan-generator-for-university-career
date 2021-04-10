<template>
  <el-container>
    <el-header>
      <NavBar />
    </el-header>
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
        :default-sort="{ prop: 'whichYear', order: 'aescending' }"
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
  </el-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "MySchedule",
  components: {
    NavBar,
  },
  methods: {
    showInfo(index, row){
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
      console.log(row.id)
      const _this = this;
    this.$axios
      .get(
        this.GLOBAL.BASE_URL+"studentChooseCourseRecord/deleteById.do",
        {
          headers: {
            //Authorization: this.$store.getters.getUserToken.accessToken,
            userId: JSON.parse(sessionStorage.getItem("userToken")).userId,
            Authorization: JSON.parse(sessionStorage.getItem("userToken"))
              .accessToken,
          },
          params: {
            id: row.id
          }
        }
      )
      .then((response) => {

      });
    },
  },
  data() {
    return {
      tableData: [],
    };
  },
  created() {
    const loading = this.$loading({
      lock: true,
      text: "Loading...",
      spinner: "el-icon-loading",
      background: "rgba(0, 0, 0, 0.7)",
    });
    const _this = this;
    this.$axios
      .get(
        this.GLOBAL.BASE_URL+"studentChooseCourseRecord/queryMyCourses.do",
        {
          headers: {
            //Authorization: this.$store.getters.getUserToken.accessToken,
            userId: JSON.parse(sessionStorage.getItem("userToken")).userId,
            Authorization: JSON.parse(sessionStorage.getItem("userToken"))
              .accessToken,
          },
        }
      )
      .then((response) => {
        _this.tableData = response.data.data;
      });
    setTimeout(() => {
      loading.close();
    }, 2000);
  },
};
</script>

<style scoped>
</style>