<template>
  <div>
    <el-header>
      <NavBar />
    </el-header>
    <div class="body">
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
      submit(){
          console.log(this.result)
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
      result:[]
    };
  },
};
</script>

<style>
.body {
  text-align: center;
}
</style>