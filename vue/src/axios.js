import axios from 'axios'
import Element from 'element-ui'

axios.defaults.baseURL = "http://localhost:8081"

axios.interceptors.request.use(config => {
    console.log("======request======")
    console.log(config)
    console.log("=========================")
    return config
})

axios.interceptors.response.use(response => {
    console.log("======response.data======")
    console.log(response.data)
    console.log("=========================")
    return response
    //console.log(res)
    /* if(res.msg === 'valid'){
        return response
    }else if(res.msg === 'not exist'){
        Element.Message.error('Username not exist')
        return Promise.reject(res.msg)
    }else{
        Element.Message.error('Incorrect password or username')
        return Promise.reject(res.msg)
    } */
},
error => {

})