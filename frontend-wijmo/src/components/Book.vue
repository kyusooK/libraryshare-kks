<template>
    <div>
        <String label="BookId" v-model="value.bookId" :editMode="editMode"/>
        <String label="Title" v-model="value.title" :editMode="editMode"/>
        <String label="Author" v-model="value.author" :editMode="editMode"/>
        <String label="Publisher" v-model="value.publisher" :editMode="editMode"/>
        <BookCategory offline label="Category" v-model="value.category" :editMode="editMode" @change="change"/>
        <BookStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        <Isbn offline label="isbn" v-model="value.isbn" :editMode="editMode" @change="change"/>

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
    name: 'Book',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "books"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

