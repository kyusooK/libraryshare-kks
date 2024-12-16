<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>도서 관리 &nbsp; ></div>
            <div>&nbsp; 도서</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="registerBookDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                <v-icon small>mdi-minus-circle-outline</v-icon>도서 등록
            </v-btn>
            <v-dialog v-model="registerBookDialog" width="500">
                <RegisterBookCommand
                    @closeDialog="registerBookDialog = false"
                    @registerBook="registerBook"
                ></RegisterBookCommand>
            </v-dialog>
            <v-btn @click="changeBookStatusDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                <v-icon small>mdi-minus-circle-outline</v-icon>도서 상태 변경
            </v-btn>
            <v-dialog v-model="changeBookStatusDialog" width="500">
                <ChangeBookStatusCommand
                    @closeDialog="changeBookStatusDialog = false"
                    @changeBookStatus="changeBookStatus"
                ></ChangeBookStatusCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','title','author','publisher','category','status','isbn',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="title" header="Title" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="author" header="Author" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="publisher" header="Publisher" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="category" header="Category" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="status" header="Status" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="isbn" header="isbn" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Isbn v-model="cell.item.isbn" :editMode="editMode"></Isbn>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
        </wj-flex-grid>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">도서 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <Book :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'bookGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'books',
        registerBookDialog: false,
        changeBookStatusDialog: false,
    }),
    watch: {
    },
    methods:{
        registerBook(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/registerbook", params)
                this.$EventBus.$emit('show-success','RegisterBook 성공적으로 처리되었습니다.')
                this.registerBookDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        changeBookStatus(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "changebookstatus", params)
                this.$EventBus.$emit('show-success','ChangeBookStatus 성공적으로 처리되었습니다.')
                this.changeBookStatusDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>