//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = './api';
const instance = axios.create({baseURL})
//添加axios请求拦截器
import { useTokenStore } from '@/stores/token'; 
import router from '@/router';

instance.interceptors.request.use(
    (config)=>{
        //在请求头中添加token
        const tokenStore = useTokenStore();
        if(tokenStore.token){
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    err=>{
        return Promise.reject(err);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        return result.data;
    },
    err=>{
        console.log('err'+err.response.status);
        if(err.response.status === 401){
            alert('请先登录');
            //token失效,跳转到登录页面
            router.push('/login');
        }else{
            alert('服务异常');
        }
        
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;