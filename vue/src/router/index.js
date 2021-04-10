import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MainMenu from '../views/MainMenu.vue'
import CourseSearch from '../views/CourseSearch.vue'
import Course from '../views/Course.vue'
import SurveyInstruction from '../views/SurveyInstruction.vue'
import Survey from '../views/Survey.vue'
import SignUp from '../views/SignUp.vue'
import MySchedule from '../views/MySchedule.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/CourseSearch',
    name: 'CourseSearch',
    component: CourseSearch
  },
  {
    path: '/MainMenu',
    name: 'MainMenu',
    component: MainMenu
  },
  {
    path: '/Course/:courseName',
    name: 'Course',
    component: Course
  },
  {
    path: '/SurveyInstruction',
    name: 'SurveyInstruction',
    component: SurveyInstruction
  },
  {
    path: '/Survey',
    name: 'Survey',
    component: Survey
  },
  {
    path: '/MySchedule',
    name: 'MySchedule',
    component: MySchedule
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
