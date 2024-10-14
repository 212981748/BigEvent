<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus';
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
const registerData=ref({
   username:'',
   password:'',
   repassword:'' 
});

const checkRepassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码!'));
       
    } else if (value !== registerData.value.password
        
    ) {
        callback(new Error('两次输入的密码不一致!'));
    
    }else {
        callback();
    }
}
const rulers={
    username: [{
        required: true,
        message: '请输入用户名',
        trigger: 'blur'
    }, {
        min: 5, 
        max: 16,
    }],
    password: [{
        required: true,
        message: '请输入密码',
        trigger: 'blur'
    }, {
        min: 6,
        max: 16,
    }],
    repassword: [{
        required: true,
        message: '请再次输入密码',
        trigger: 'blur'
    }, {
        min: 6,
        max: 16,
       
    },{ 
        validator: checkRepassword ,
        trigger: 'blur'}],
    
}
//调用后台接口完成注册
import{userRegisterService} from '@/api/user.js'
import { userLoginService } from '@/api/user.js'
import{ useRouter } from 'vue-router'
const router = useRouter()
const register=async()=>{
    if(registerData.value.password==registerData.value.repassword){
        let result=await userRegisterService(registerData.value)
if(result.code===0){
   ElMessage.success(result.msg?result.msg:'注册成功！')
   ClearData();
    //注册成功后，清空表单数据，并切换到登录页面
    isRegister.value=false
    //清空表单数据
}else{
    ElMessage.error(result.msg?result.msg:'注册失败 !')
}



    }else{
        ElMessage.error('注册失败！')
    }

};


import { useTokenStore } from '@/stores/token';
const tokenStore = useTokenStore();
const login=async()=>{
    let result=await userLoginService(registerData.value)
    if(result.code===0){    
        ElMessage.success('登录成功！')
        //登录成功后，跳转到首页}
        router.push('/article/manage')
        tokenStore.setToken(result.data)
    }
        
    else{ElMessage.error('账号或密码错误！')}
}



const handleRegisterClick = () => {
   ClearData(); // 先清空数据
    console.log('Clear');
    isRegister.value = true; // 再切换到注册状态
};



const ClearData=()=>{
    registerData.value={
        username:'',
        password:'',
        repassword:'',
        
    }
    console.log('ClearData被执行')
    console.log('666666',registerData.value.username) 
}
//调用后台接口完成登录
</script>


<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rulers">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username" ></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item prop="repassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="registerData.repassword"></el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false;ClearData()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>
            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else  :model="registerData" :rules="rulers">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名"v-model="registerData.username"></el-input>
                </el-form-item>
                <el-form-item   prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>记住我</el-checkbox>
                        <el-link type="primary" :underline="false">忘记密码？</el-link>
                    </div>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="handleRegisterClick ">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
            url('@/assets/login_bg.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>