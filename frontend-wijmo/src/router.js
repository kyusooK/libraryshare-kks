
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BookManager from "./components/ui/BookGrid"

import LoanManager from "./components/ui/LoanGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/books',
                name: 'BookManager',
                component: BookManager
            },

            {
                path: '/loans',
                name: 'LoanManager',
                component: LoanManager
            },



    ]
})
