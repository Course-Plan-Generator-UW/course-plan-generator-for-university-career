<template>
  <div class="common-layout" style="text-align: center">
    <el-container>
      <el-header>
        <NavBar />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-collapse>
            <el-collapse-item title="Subject" name="1">
              <div>
                <el-select
                  v-model="subjectVal"
                  multiple
                  placeholder="Choose a subject"
                  @change="print"
                >
                  <el-option
                    v-for="item in subjects"
                    :key="item.value"
                    :label="item.value"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </div>
            </el-collapse-item>
            <el-collapse-item title="Capacity" name="2">
              <div>
                <el-input-number
                  v-model="minCapacity"
                  :step="50"
                  :min="0"
                ></el-input-number>
              </div>
              <div>to</div>
              <div>
                <el-input-number
                  v-model="maxCapacity"
                  :step="50"
                ></el-input-number>
              </div>
            </el-collapse-item>
            <el-collapse-item title="Average GPA" name="3">
              <div>
                <el-input-number
                  v-model="minGPA"
                  :precision="2"
                  :step="0.1"
                  :min="0"
                  :max="4"
                ></el-input-number>
              </div>
              <div>to</div>
              <div>
                <el-input-number
                  v-model="maxGPA"
                  :precision="2"
                  :step="0.1"
                  :min="0"
                  :max="4"
                ></el-input-number>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-aside>
        <el-container direction="vertical">
          <div id="head">
            <div>
              <h2>Course Search</h2>
            </div>

            <div style="display: inline-block">
              <el-input
                placeholder="Keyword"
                v-model="search"
                class="input-with-select"
              >
                <template #prepend>
                  <el-select
                    v-model="searchBy"
                    placeholder="Search By"
                    style="width: 150px"
                  >
                    <el-option
                      label="Course Name"
                      value="courseName"
                    ></el-option>
                  </el-select>
                </template>
                <template #append>
                  <el-button
                    icon="el-icon-search"
                    @click="courseSearch"
                  ></el-button>
                </template>
              </el-input>
            </div>
            <!-- <div style="display: inline-block; margin-right: 150px">
              <el-select
                v-model="subjectVal"
                multiple
                placeholder="Choose a subject"
                @change="print"
              >
                <el-option
                  v-for="item in subjects"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
            <div style="display: inline-block">
              <el-select
                v-model="tagVal"
                multiple
                placeholder="Choose a subject"
              >
                <el-option
                  v-for="item in subjects"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div> -->
          </div>
          <!-- <el-button type="primary" @click="add" style="width: 150px; margin: 0 auto">Add</el-button>
      <el-button type="primary" @click="clear" style="width: 150px; margin: 0 auto">Clear</el-button> -->
          <div
            style="
              text-align: center;
              height: 50%;
              margin-left: 150px;
              margin-right: 150px;
            "
          >
            <el-table
              :data="
                tableData.slice(
                  (currentPage - 1) * pageSize,
                  currentPage * pageSize
                )
              "
              style="width: 100%"
              border
              ref="table"
              empty-text="No result found"
              @row-click="handle"
              @sort-change="sortChange"
            >
              <el-table-column
                prop="courseName"
                label="Course Name"
                sortable="custom"
              >
              </el-table-column>
              <el-table-column prop="subject" label="Subject" sortable="custom">
              </el-table-column>
              <el-table-column
                prop="capability"
                label="Capability"
                sortable="custom"
              >
              </el-table-column>
              <el-table-column prop="GPA" label="Avg. GPA" sortable="custom">
              </el-table-column>
            </el-table>
          </div>
          <div>
            <el-pagination
              background
              layout="prev, pager, next"
              :total="tableData.length"
              :page-size="pageSize"
              :current-page="currentPage"
              :hide-on-single-page="true"
              @current-change="pageChange"
            >
            </el-pagination>
          </div>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "CourseSearch",
  components: {
    NavBar,
  },
  data() {
    return {
      subjects: [],
      tableData: [],
      subjectVal: [],
      tagVal: [],
      search: "",
      searchBy: "",
      currentPage: 1,
      pageSize: 20,
      minCapacity: 0,
      maxCapacity: 10000,
      minGPA: 0.0,
      maxGPA: 4.0,
    };
  },
  methods: {
    print() {
      console.log(this.search);
      console.log(this.$refs.table);
      this.$refs.table.doLayout();
    },
    formatter(row, column) {
      return row.address;
    },
    handle(row, event, column) {
      console.log(row.courseNum);
      //console.log(column)
      //console.log(event)
      this.$router.push("/Course?id=" + row.courseNum);
    },
    add() {
      this.tableData.push({
        courseNum: "CS 506",
        courseName: "Software Engineering",
        professor: "Tracy",
      });
    },
    clear() {
      this.tableData = [];
    },
    pageChange(currentPage) {
      this.currentPage = currentPage;
    },
    /* sortChange(arg){
      console.log(arg)
    }, */

    sortChange(column) {
      console.log(column);
      this.currentPage = 1;
      this.tableData = this.tableData.sort(
        this.sortFun(column.prop, column.order === "ascending")
      );
      /* console.log(this.tableData)
            console.log('Finished') */
    },

    sortFun(attr, rev) {
      if (rev == undefined) {
        rev = 1;
      } else {
        rev = rev ? 1 : -1;
      }

      return function (a, b) {
        a = a[attr];
        b = b[attr];
        if (a < b) {
          return rev * -1;
        }
        if (a > b) {
          return rev * 1;
        }
        return 0;
      };
    },
    courseSearch() {
      console.log(this.subjectVal[0])
      const loading = this.$loading({
        lock: true,
        text: "Searching...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      var searchRequest = {};
      searchRequest.totalStudentsLimitMin = this.minCapacity;
      searchRequest.totalStudentsLimitMax = this.maxCapacity;
      searchRequest.avgGpaLimitMin = this.minGPA;
      searchRequest.avgGpaLimitMax = this.maxGPA;
      if(this.subjectVal[0]){
        searchRequest.subjectArrStr = this.subjectVal.join(";")
      }
      if (this.search != "" && this.searchBy != "") {
        searchRequest[this.searchBy] = this.search;
      }
      const _this = this;
      this.$axios
        .get("http://127.0.0.1:8081/courseInfo/queryPage.do", {
          params: searchRequest,
        })
        .then((response) => {
          _this.tableData = [];
          const courseData = response.data.data.records;
          courseData.forEach(function (course) {
            _this.tableData.push({
              courseName: course.courseName,
              subject: course.subject,
              capability: course.totalStudents,
              GPA: course.avgGpa,
            });
          });
        });
      /*
      this.$axios
        .get("http://127.0.0.1:8081/", {
          // put the right URL here
          params: {},
        })
        .then((response) => {
          _this.subjects = [];
          const courseSubjects = response.data.data.records;
          courseSubjects.forEach(function (subject) {
            _this.subjects.push({
              value: subject.value,
            });
          });
        });*/
      setTimeout(() => {
        loading.close();
      }, 2000);
    },
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
      .get("http://127.0.0.1:8081/courseInfo/queryPage.do", {
        params: {},
      })
      .then((response) => {
        const courseData = response.data.data.records;
        courseData.forEach(function (course) {
          _this.tableData.push({
            courseName: course.courseName,
            subject: course.subject,
            capability: course.totalStudents,
            GPA: course.avgGpa,
          });
        });
      });

      this.$axios
            .get("http://127.0.0.1:8081/courseInfo/queryAllSubject.do", {
              params: {},
            })
            .then((response) => {
              const subjectData = response.data.data;
              for(var index in subjectData){
                        _this.subjects.push({
                               value:subjectData[index]
                              });
              }
            });

    setTimeout(() => {
      loading.close();
    }, 2000);
  },
};
</script>


<style scoped>
/* template .el-table th.gutter {
  display: table-cell !important;
  width: 150px !important;
}
template .el-table colgroup.gutter {
  display: table-cell !important;
} */

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

html,
body {
  margin: 0px;
  height: 90%;
}
table {
  display: inline-block;
}
th {
  width: 31.5%;
}
#head {
  margin: 50px 0 100px 0;
}
#tags {
  width: fit-content;
  height: 150px;
  overflow: scroll;
  background-color: white;
  border: 1px solid;
  margin: 0;
  position: absolute;
  list-style: none;
  padding: 0;
  display: none;
}
#subject {
  width: fit-content;
  background-color: white;
  border: 1px solid;
  margin: 0;
  position: absolute;
  list-style: none;
  padding: 0;
  display: none;
}
#search-box {
  width: 300px;
  height: 30px;
  margin-right: 200px;
}

.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  background-color: #b3c0d1;
  color: #333;
}
</style>