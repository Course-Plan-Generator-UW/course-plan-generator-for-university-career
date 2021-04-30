<template>
  <el-container>
    <el-header>
      <NavBar />
    </el-header>
    <h2 v-html="data.courseName"></h2>
    <h3 v-html="data.subject"></h3>
    <p>/* This is the description */</p>
    <el-form :model="data" ref="data">
      <el-form-item label="Average GPA:">
        <span>{{ data.avgGpa }} </span>
      </el-form-item>
      <el-form-item label="Total Students:">
        <span>{{ data.totalStudents }} </span>
      </el-form-item>
    </el-form>
    <div>
      <p>Current Rate:</p>
      <el-rate
        v-model="curRate"
        disabled
        show-score
        text-color="#ff9900"
        score-template="{value}"
      >
      </el-rate>
    </div>
    <div>
      <p>Rate this course:</p>
      <el-rate
        v-model="userRate"
        show-text
        :texts="['Poor', 'Unsatisfied', 'Average', 'Satisfied', 'Amazing']"
        ref="rate"
        @change="submitRate()"
      >
      </el-rate>
    </div>
    <div>
      <p>Course Tags:</p>
      <el-tag
        v-for="tag in dynamicTags"
        :key="tag"
        :disable-transitions="false"
      >
        {{ tag }}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput"
        >+ New Tag</el-button
      >
    </div>
  </el-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "Course",
  components: {
    NavBar,
  },
  methods: {
    submitRate() {
      this.$refs["rate"].disabled = true;
      console.log(this.userRate);
      this.$axios
        .post(this.GLOBAL.BASE_URL+"courseStudentRate/insert.do", {
          courseName: this.data.courseName,
          studentId: JSON.parse(sessionStorage.getItem("userToken")).userId,
          rate: this.userRate,
        })
        .then((response) => {});
    },
    /* agreeTag(index) {
      this.dynamicTags[index].number++;
    },
    refuseAgree() {
      this.$notify({
        title: "Warning",
        message: "You have already agreed with this tag",
        type: "warning",
      });
    }, */
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (!inputValue) {
        this.$notify({
          title: "Warning",
          message: "Cannot upload empty tag",
          type: "warning",
        });
      } else if (this.checkExist(inputValue)) {
        this.$notify({
          title: "Warning",
          message: "Tag exists",
          type: "warning",
        });
      } else {
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = "";
        const _this = this;
        this.$axios
          .post(this.GLOBAL.BASE_URL+"courseStudentTag/insert.do", {
            courseName: this.data.courseName,
            studentId: JSON.parse(sessionStorage.getItem("userToken")).userId,
            tag: inputValue,
          })
          .then((response) => {});
      }
    },
    checkExist(inputValue){
      var exist = false
      this.dynamicTags.forEach(tag => {
        if(inputValue===tag){
          exist = true
        }
      });
      return exist;
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
  },
  data() {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: "",
      curRate: 0,
      userRate: null,
      data: {
        avgGPA: "",
        courseName: "",
        subject: "",
      },
    };
  },
  created() {
    const courseName = this.$route.params.courseName;
    const loading = this.$loading({
      lock: true,
      text: "Loading...",
      spinner: "el-icon-loading",
      background: "rgba(0, 0, 0, 0.7)",
    });
    const _this = this;
    this.$axios
      .get(this.GLOBAL.BASE_URL+"courseInfo/queryPage.do", {
        params: { courseName: courseName },
        headers: {
          //Authorization: this.$store.getters.getUserToken.accessToken,
          Authorization: JSON.parse(sessionStorage.getItem("userToken"))
            .accessToken,
        },
      })
      .then((response) => {
        this.data = response.data.data.records[0];
        if (this.data.avgRate) {
          this.curRate = this.data.avgRate;
        }
        this.data.avgGpa = Math.floor(this.data.avgGpa * 1000) / 1000
        //console.log(this.data);
      });
    this.$axios
      .get(
        this.GLOBAL.BASE_URL+"courseStudentRate/queryByCourseNameAndStudentId.do",
        {
          params: {
            courseName: courseName,
            userId: JSON.parse(sessionStorage.getItem("userToken")).userId,
          },
        }
      )
      .then((response) => {
        if (response.data.data) {
          this.userRate = response.data.data;
          this.$refs["rate"].disabled = true;
        }
      });
      this.$axios
      .get(
        this.GLOBAL.BASE_URL+"courseStudentTag/queryAll.do",
        {
          params: {
            courseName: courseName,
            userId: JSON.parse(sessionStorage.getItem("userToken")).userId,
          },
        }
      )
      .then((response) => {
        if (response.data.data) {
          response.data.data.forEach(item => {
            this.dynamicTags.push(item.tag);
          })
        }
      });
    setTimeout(() => {
      loading.close();
    }, 2000);
  },
};
</script>

<style scoped>
.item {
  margin-top: 10px;
  margin-right: 10px;
  display: inline;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>