import Vue from 'vue'
import Router from 'vue-router'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _58f88777 = () => interopDefault(import('..\\pages\\course\\index.vue' /* webpackChunkName: "pages_course_index" */))
const _aaac85ee = () => interopDefault(import('..\\pages\\login.vue' /* webpackChunkName: "pages_login" */))
const _0613599a = () => interopDefault(import('..\\pages\\register.vue' /* webpackChunkName: "pages_register" */))
const _2efc5b40 = () => interopDefault(import('..\\pages\\teacher\\index.vue' /* webpackChunkName: "pages_teacher_index" */))
const _1dc008c2 = () => interopDefault(import('..\\pages\\course\\_id.vue' /* webpackChunkName: "pages_course__id" */))
const _78682da8 = () => interopDefault(import('..\\pages\\teacher\\_id.vue' /* webpackChunkName: "pages_teacher__id" */))
const _3dfbcc1c = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages_index" */))

// TODO: remove in Nuxt 3
const emptyFn = () => {}
const originalPush = Router.prototype.push
Router.prototype.push = function push (location, onComplete = emptyFn, onAbort) {
  return originalPush.call(this, location, onComplete, onAbort)
}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: decodeURI('/'),
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/course",
    component: _58f88777,
    name: "course"
  }, {
    path: "/login",
    component: _aaac85ee,
    name: "login"
  }, {
    path: "/register",
    component: _0613599a,
    name: "register"
  }, {
    path: "/teacher",
    component: _2efc5b40,
    name: "teacher"
  }, {
    path: "/course/:id",
    component: _1dc008c2,
    name: "course-id"
  }, {
    path: "/teacher/:id",
    component: _78682da8,
    name: "teacher-id"
  }, {
    path: "/",
    component: _3dfbcc1c,
    name: "index"
  }],

  fallback: false
}

export function createRouter () {
  return new Router(routerOptions)
}
