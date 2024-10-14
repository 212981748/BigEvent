<template>
  <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="auto"
    class="demo-ruleForm"
  >
    <el-form-item label="旧密码:" prop="oldPass" placeholder="请输入旧密码">
      <el-input v-model="ruleForm.oldPass" type="password" autocomplete="off" />
    </el-form-item>
    
    <el-form-item label="新密码:" prop="pass" placeholder="请输入新密码">
      <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
    </el-form-item>
    
    <el-form-item label="确认新密码:" prop="checkPass" placeholder="请确认新密码">
      <el-input
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)" :loading="loading">
        提交
      </el-button>
      <el-button @click="resetForm(ruleFormRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { updatePasswordService } from '@/api/user';
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { th } from 'element-plus/es/locale';
const router = useRouter()
const ruleFormRef = ref<FormInstance>()
const loading = ref(false)

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入新密码'))
  } else {
    callback()
  }
}

const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请确认新密码'))
  } else if (value !== ruleForm.pass) {
    callback(new Error('两个输入的密码不一致'))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  pass: '',
  checkPass: '',
  oldPass: ''
})

const rules = reactive<FormRules<typeof ruleForm>>({
  pass: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  oldPass: [{ required: true, message: '请输入旧密码', trigger: 'blur' }]
})

const submitForm = async(formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async(valid) => {
    if (valid) {
      loading.value = true
      const updateData = {
        old_pwd: ruleForm.oldPass,
        new_pwd: ruleForm.pass,
        re_pwd: ruleForm.checkPass
      }
      try {
        let res = await updatePasswordService(updateData)
        console.log('提交结果：', res)
        console.log('提交结果：', JSON.stringify(res, null, 2))
        if (res.code === 0) {
          ElMessage.success('密码重置成功！')
          alert('密码重置成功请重新登录！')
          // 重置路由，回到登录页
          router.push({ path: '/login' })
        } else {
          ElMessage.error('密码重置失败！')
        }
      } catch (error) {
        ElMessage.error('请求失败，请稍后重试！')
      } finally {
        loading.value = false
      }
    } else {
      ElMessage.error('表单验证失败，请检查输入！')
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>
