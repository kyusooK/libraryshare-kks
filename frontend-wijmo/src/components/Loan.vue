<template>
    <div>
        <Number label="LoanId" v-model="value.loanId" :editMode="editMode"/>
        <Date label="LoanDate" v-model="value.loanDate" :editMode="editMode"/>
        <Date label="DueDate" v-model="value.dueDate" :editMode="editMode"/>
        <Member offline label="회원" v-model="value.member" :editMode="editMode" @change="change"/>
        <BookReference offline label="도서 참조" v-model="value.bookReference" :editMode="editMode" @change="change"/>
        <LoanPeriod offline label="대출 기간" v-model="value.loanPeriod" :editMode="editMode" @change="change"/>
        <LoanStatus offline label="LoanStatus" v-model="value.loanStatus" :editMode="editMode" @change="change"/>
        <BookId offline label="bookId" v-model="value.bookId" :editMode="editMode" @change="change"/>


        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'Loan',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "loans"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

