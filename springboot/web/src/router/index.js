import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MainMenu from '../views/MainMenu.vue'
import CourseSearch from '../views/CourseSearch.vue'
import Course from '../views/Course.vue'
import SurveyInstruction from '../views/SurveyInstruction.vue'
import Survey from '../views/Survey.vue'
import SignUp from '../views/SignUp.vue'

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
    path: '/Course',
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
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
