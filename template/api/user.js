import request from '@/utils/request'
const TAG = "/user"
export default {
    sendsms(mobile){
        return request({
            url: TAG + "/sendsms/" + mobile,
            method: 'POST'
        })
    },
    register(user,code){
        return request({
            url: TAG + "/register/" + code,
            method: 'POST',
            data: user
        })
    },
    login(username,password){
        return request({
            url: TAG + "/login",
            method: 'POST',
            data: {
                username,
                password
            }
        })
    },
    info(){
       return request({
           url: TAG + "/info",
           method: 'GET'
       }) 
    },
}