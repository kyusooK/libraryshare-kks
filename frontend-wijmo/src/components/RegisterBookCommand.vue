<template>

    <v-card outlined>
        <v-card-title>
            RegisterBook
        </v-card-title>

        <v-card-text>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Author" v-model="value.author" :editMode="editMode"/>
            <String label="Publisher" v-model="value.publisher" :editMode="editMode"/>
            <BookCategory offline label="Category" v-model="value.category" :editMode="editMode" @change="change"/>
            <String label="BookId" v-model="value.bookId" :editMode="editMode"/>
            <Isbn offline label="Isbn" v-model="value.isbn" :editMode="editMode" @change="change"/>
            <BookStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="registerBook"
            >
                RegisterBook
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
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
        name: 'RegisterBookCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.title = '';
            this.value.author = '';
            this.value.publisher = '';
            this.value.category = {};
            this.value.bookId = '';
            this.value.isbn = {};
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            registerBook() {
                this.$emit('registerBook', this.value);
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

