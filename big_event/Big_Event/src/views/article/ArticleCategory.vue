<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ElMessageBox } from 'element-plus'
import { ref } from 'vue'
const categorys = ref([
    {
        "id": 3,
        "categoryName": "美食",
        "categoryAlias": "my",
        "createTime": "2023-09-02 12:06:59",
        "updateTime": "2023-09-02 12:06:59"
    },
    {
        "id": 4,
        "categoryName": "娱乐",
        "categoryAlias": "yl",
        "createTime": "2023-09-02 12:08:16",
        "updateTime": "2023-09-02 12:08:16"
    },
    {
        "id": 5,
        "categoryName": "军事",
        "categoryAlias": "js",
        "createTime": "2023-09-02 12:08:33",
        "updateTime": "2023-09-02 12:08:33"
    }
   
])
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const categoryModel = ref({
    categoryName: '',
    categoryAlias: ''
})
//添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}
//声明一个异步函数，用来获取文章分类数据
import{articleCategoryListService,addArticleService,updateArticleService,deleteArticleService} from '@/api/article.js'

const articleCategoryList=async()=>{
    let result=await articleCategoryListService();
    console.log('result返回结果',result)
categorys.value=result.data;
console.log('66666666'+categorys.value)
}
articleCategoryList()
//添加分类
const addCategory=async()=>{
    //调用接口
    let result=await addArticleService(categoryModel.value)
    console.log('添加分类返回结果',result)
    //提示    
    if(result.code!==0){
        alert(result.msg)
        return
    }
    //刷新页面
    articleCategoryList()
    //关闭弹窗
    dialogVisible.value = false
}
const title='修改分类'
const updateGetCategory=async(row)=>{
    categoryModel.value={...row}
    console.log('修改分类返回结果',categoryModel.value)
    dialogVisible.value = true;
    
}
const updateCategory=async()=>{
    //调用接口
    let result=await updateArticleService(categoryModel.value)
    dialogVisible.value = false;
    articleCategoryList()
}
const DeleteCategory=async(row)=>{
         categoryModel.value={...row}
        console.log('删除分类返回结果',categoryModel.value)
       try {
          await ElMessageBox.confirm('确定删除该分类吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
        });
        
        let result=await deleteArticleService(categoryModel.value.id)
        console.log('删除分类返回结果',result)
        articleCategoryList();
    }catch(error){
        console.log('删除失败')
    }
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary"@click="dialogVisible = true;title='添加分类'">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"@click="updateGetCategory(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="DeleteCategory(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
    </el-card>
    <el-dialog v-model="dialogVisible" :title="title" width="30%">
    <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
        <el-form-item label="分类名称" prop="categoryName">
            <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="分类别名" prop="categoryAlias">
            <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
        </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="title==='添加分类'?addCategory():updateCategory();dialogVisible = false"> 确认 </el-button>
        </span>
    </template>
</el-dialog>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>