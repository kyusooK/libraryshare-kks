<template>
    <v-card outlined>
        <v-card-title>
            CreateLoan
        </v-card-title>

        <v-card-text>
            <Number label="MemberId" v-model="value.memberId" :editMode="editMode"/>
            <Number label="BookId" v-model="value.bookId" :editMode="editMode"/>
            <LoanPeriod offline label="LoanPeriod" v-model="value.loanPeriod" :editMode="editMode" @change="change"/>
            <Date label="LoanDate" v-model="value.loanDate" :editMode="editMode"/>
            <Date label="DueDate" v-model="value.dueDate" :editMode="editMode"/>
            <Number label="LoanId" v-model="value.loanId" :editMode="editMode"/>
            <Member offline label="Member" v-model="value.member" :editMode="editMode" @change="change"/>
            <BookReference offline label="BookReference" v-model="value.bookReference" :editMode="editMode" @change="change"/>
            <LoanStatus offline label="LoanStatus" v-model="value.loanStatus" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createLoan"
            >
                CreateLoan
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreateLoanCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.memberId = 0;
            this.value.bookId = 0;
            this.value.loanPeriod = {};
            this.value.loanDate = '2024-12-16';
            this.value.dueDate = '2024-12-16';
            this.value.loanId = 0;
            this.value.member = {};
            this.value.bookReference = {};
            this.value.loanStatus = {};
        },
        watch: {
        },
        methods: {
            createLoan() {
                this.$emit('createLoan', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

