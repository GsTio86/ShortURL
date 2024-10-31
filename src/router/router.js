import {createRouter, createWebHistory} from "vue-router";
import Home from '@/views/Home.vue';

const routes = [
    { path: '/', component: Home },
    { path: '/api/:pathMatch(.*)*', component: () => null },
    { path: '/:url([a-zA-Z0-9]{7})', component: () => null },
    { path: '/:any(.*)*', redirect: '/' }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;