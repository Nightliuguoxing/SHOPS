<template>
    <div>
        活动列表
        <div v-for="(item, index) in items" :key = "index">
            <nuxt-link :to="'/gather/item/'+item.id">{{item.name}}</nuxt-link>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    asyncData() {
        return axios.get('http://localhost:9000/gather').then(res => {
            return {
                items: res.data.data
            }
        })
    },
}
</script>