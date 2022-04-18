import request from '@/utils/request'

const TAG = '/gather'

export default {
    // 查询标签列表
    getList(){
        return request({
            url: TAG,
            method: 'GET'
        })
    },
    // SEARCH 分页查询标签
    search(page, size, map){
        return request({
            url: TAG + '/search/' + page + '/' + size,
            method: 'POST',
            data: map
        })
    },
    // 根据ID查询标签
    findById(id){
        console.log(TAG + '/' + id)
        return request({
            url: TAG + '/' + id,
            method: 'GET'
        })
    },
    // 新增标签
    insert(data){
        return request({
            url: TAG,
            method: 'POST',
            data
        })
    },
    // 修改标签
    update(id, data) {
        return request({
            url: TAG + '/' + id,
            method: 'PUT',
            data
        })
    },
    // 删除标签
    deleteById(id){
        return request({
            url: TAG + '/' + id,
            method: 'DELETE'
        })
    }
}