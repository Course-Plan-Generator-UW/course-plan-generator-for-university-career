<template>
  <div class="body">
    <Login/>
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import Login from '@/components/Login.vue'

export default {
  name: 'Home',
  components: {
    Login
  }
}
</script>

<style scoped>
  .body {
        margin: 0 auto;
        padding: 0;
        width: 100%;
        height: 100%;
        display: flex;
        top: 50%
      }
</style>
