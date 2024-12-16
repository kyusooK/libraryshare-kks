<template>
    <v-card outlined>
        <v-card-title>
            ReturnLoan
        </v-card-title>

        <v-card-text>
            <Number label="LoanId" v-model="value.loanId" :editMode="editMode"/>
            <Date label="ReturnDate" v-model="value.returnDate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="returnLoan"
            >
                ReturnLoan
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
        name: 'ReturnLoanCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.loanId = 0;
            this.value.returnDate = '2024-12-16';
        },
        watch: {
        },
        methods: {
            returnLoan() {
                this.$emit('returnLoan', this.value);
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

