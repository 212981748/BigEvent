import  request  from '@/utils/request.js';
import { useTokenStore } from '@/stores/token';
export const articleCategoryListService = () => {
  // const tokenStore = useTokenStore();
  // return request.get('/category/categorylist',{headers: {'Authorization':tokenStore.token}} );
  return request.get('/category/categorylist');
};
export const addArticleService = (categoryData) => {
  return request.post('category/categoryadd',categoryData);
};
export const updateArticleService = (categoryData) => {
  return request.put('category/categoryupdate',categoryData);
};
export const deleteArticleService = (id) => {
  console.log('*****************************************',id);
  return request.delete('category/delete/',{params:{id:id}});
};
export const articleListService = (params) => {
  return request.get('/category/categorylist',{params:params});
};
export const articlefindListService = (params) => {
  return request.get('/article/findlist',{params:params});
}
export const articleAddService = (articleModel) => {
  return request.post('/article/articleadd',articleModel);
}
export const articleDeleteService = (id) => {
  return request.delete('/article/delete/',{params:{id:id}});
}
export const articleUpdateService = (articleModel) => {
  return request.put('/article/articleupdate',articleModel);
}

