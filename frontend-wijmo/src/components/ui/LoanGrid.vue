<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>대출/반납 관리 &nbsp; ></div>
            <div>&nbsp; 대출</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="createLoanDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-minus-circle-outline</v-icon>대출 신청
            </v-btn>
            <v-dialog v-model="createLoanDialog" width="500">
                <CreateLoanCommand
                    @closeDialog="createLoanDialog = false"
                    @createLoan="createLoan"
                ></CreateLoanCommand>
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
            <wj-flex-grid-filter :filterColumns="['RowHeader','member','bookReference','loanPeriod','loanStatus','loanDate','dueDate',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="member" header="회원" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Member v-model="cell.item.member" :editMode="editMode"></Member>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="bookReference" header="도서 참조" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <BookReference v-model="cell.item.bookReference" :editMode="editMode"></BookReference>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="loanPeriod" header="대출 기간" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <LoanPeriod v-model="cell.item.loanPeriod" :editMode="editMode"></LoanPeriod>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="loanStatus" header="LoanStatus" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="loanDate" header="LoanDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="dueDate" header="DueDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="bookId" header="도서" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <BookId v-model="cell.item.bookId" :editMode="editMode"></BookId>
                    {{ cell.item.bookId }}
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
                            <div style="color:white; font-size:17px; font-weight:700;">대출 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <Loan :offline="offline"
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
    name: 'loanGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'loans',
        createLoanDialog: false,
    }),
    watch: {
    },
    methods:{
        createLoan(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/createloan", params)
                this.$EventBus.$emit('show-success','CreateLoan 성공적으로 처리되었습니다.')
                this.createLoanDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>