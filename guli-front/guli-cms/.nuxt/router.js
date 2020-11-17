import Vue from 'vue'
import Router from 'vue-router'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _aaac85ee = () => interopDefault(import('..\\pages\\login.vue' /* webpackChunkName: "pages_login" */))
const _0613599a = () => interopDefault(import('..\\pages\\register.vue' /* webpackChunkName: "pages_register" */))
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
    path: "/login",
    component: _aaac85ee,
    name: "login"
  }, {
    path: "/register",
    component: _0613599a,
    name: "register"
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
