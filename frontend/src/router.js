
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BookManagementBookManager from "./components/listers/BookManagementBookCards"
import BookManagementBookDetail from "./components/listers/BookManagementBookDetail"

import LoanManagementLoanManager from "./components/listers/LoanManagementLoanCards"
import LoanManagementLoanDetail from "./components/listers/LoanManagementLoanDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/bookManagements/books',
                name: 'BookManagementBookManager',
                component: BookManagementBookManager
            },
            {
                path: '/bookManagements/books/:id',
                name: 'BookManagementBookDetail',
                component: BookManagementBookDetail
            },

            {
                path: '/loanManagements/loans',
                name: 'LoanManagementLoanManager',
                component: LoanManagementLoanManager
            },
            {
                path: '/loanManagements/loans/:id',
                name: 'LoanManagementLoanDetail',
                component: LoanManagementLoanDetail
            },



    ]
})
