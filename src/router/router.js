import {createRouter, createWebHistory} from "vue-router";
import Home from '@/views/Home.vue';

const routes = [
    { path: '/', component: Home },
    { path: '/:any(.*)*', redirect: '/' }
]

const router = createRouter({
    history: createWebHistory('/tools/'),
    routes,
})

export default router;