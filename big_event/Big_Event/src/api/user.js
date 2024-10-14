//导入request.js文件，用于发送请求
import request from '@/utils/request.js';
//提供调用注册接口的函数
export const userRegisterService = (registerData) => {
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key]);
    }
   return request.post('/user/register', params);
}

export const userLoginService = (loginData) => {
    request.post('/user/login', loginData);
    const params = new URLSearchParams();
    for (let key in loginData) {
        params.append(key, loginData[key]);
    }
    return request.post('/user/login', params);
};
    
//提供调用获取用户信息的函数
export const getUserInfoService = () => {
    return request.get('/user/userInfo');
};
export const updateUserInfoService = (userInfo) => {
    return request.put('/user/update', userInfo);
};
//提供调用修改密码的函数
export const updatePasswordService = (updateData) => {  
    console.log('updatePasswordService被调用了')
    console.log('55555555555',updateData)        
    return request.patch('/user/updatePwd',updateData);
    ;
};