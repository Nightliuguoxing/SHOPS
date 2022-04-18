## SHOPS 

##### 2022-04-15

```bash
POST 	/user/login
POST 	/user/info
```

##### 2022-04-16

```bash
标签管理
GET	/label
GET	/label/{id}
POST 	/label
POST	/label/search
POST	/label/search/{page}/{size}
PUT 	/label/{id}
DELETE	/label/{id}
```
##### 2022-04-17
```bash
管理员管理
GET	/admin
GET	/admin/{id}
POST 	/admin
POST	/admin/search
POST	/admin/search/{page}/{size}
PUT 	/admin/{id}
DELETE	/admin/{id}

活动管理
GET	/gather
GET	/gather/{id}
POST 	/gather
POST	/gather/search
POST	/gather/search/{page}/{size}
PUT 	/gather/{id}
DELETE	/gather/{id}
```


## SHOPS-ADMIN 

##### 2022-04-15

```bash
- Login.vue
    - 用户登录
- index.vue
    - 根据{TOKEN}获取用户信息
```

##### 2022-04-16

```bash
- Label.vue
    - 查询标签列表
    - 根据ID查询标签
    - 条件查询标签
    - 根据ID修改标签
    - 根据ID修改标签推荐
    - 根据ID修改标签状态
    - 根据ID删除标签
```
##### 2022-04-17

```bash
- Admin.vue
	- 查询管理员列表
    - 根据ID查询管理员
    - 条件查询管理员
    - 根据ID修改管理员
    - 根据ID修改管理员状态
    - 根据ID删除管理员
- Gather.vue
	- 查询活动列表
    - 根据ID查询活动
    - 条件查询活动
    - 根据ID修改活动
    - 根据ID修改活动状态
    - 根据ID删除活动
```

##  SHOPS-WEB 