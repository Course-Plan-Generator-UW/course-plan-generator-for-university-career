import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userToken: {},
    userInfo: {}
  },
  mutations: {
    SET_USERTOKEN: (state, userToken) =>{
      state.userToken = userToken
      sessionStorage.setItem("userToken", JSON.stringify(userToken))
    },
    REMOVE_USERTOKEN: (state) => {
      state.userToken = {}
      sessionStorage.setItem("userToken", JSON.stringify(''))
    },
    SET_USERINFO: (state, userInfo) =>{
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
    },
    REMOVE_USERINFO: (state) => {
      state.userInfo = {}
      sessionStorage.setItem("userInfo", JSON.stringify(''))
    },
  },
  getters:{
    getUserToken: state => {
      return state.userToken
    },
    getUserInfo: state => {
      return state.userInfo
    },
  },
  actions: {
  },
  modules: {
  }
})
