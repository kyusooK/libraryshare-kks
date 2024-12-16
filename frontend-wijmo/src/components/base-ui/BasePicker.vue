<template>
    <div>
        <v-combobox
            :items="list"
            :item-text="getItemText"
            :item-value="idField"
            :label="label"
            return-object
            v-model="selected"
            @change="select"
            solo
        ></v-combobox>
    </div>
</template>

<script>
import BaseRepository from '../../repository/BaseRepository';
const axios = require('axios').default;

var _ = require('lodash');

export default {
    name: 'BasePicker',
    components:{},
    props: {
        value: [String, Object, Array, Number, Boolean],
        editMode: Boolean,
        label: String,
        path: String,
        nameField: String,
        idField: String,
        searchApiPath: String,
        searchParameterName: String,
    },
    data: () => ({
        list: [],
        selected: null,
        referenceValue: null,
        repository: null,
        searchKeyword:null,
    }),
    async created() {
        
        var me = this;
        this.repository = new BaseRepository(axios, this.path)

        if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
            
            var id = me.value[me.idField];
            var tmpValue = await this.repository.findById(id)
            if(tmpValue.data) {
                var val = tmpValue.data
                
                me.referenceValue = val
            }
        }
        if(this.editMode){
            this.fillSelections()
        }
        
    },
    mounted() {
        this.$EventBus.$on('changeSelected', (dialog) => {
            if (!dialog) {
                this.selected = null;
            }
        });
    },
    watch:{
        "selected": {
            handler: _.debounce(async function () {
                
            }, 500),
            immediate: true 
        },
        "searchKeyword": {
            deep: true,
            handler: _.debounce (async function(){
                var me = this;
                var temp = null
                let query = {
                    apiPath: me.searchApiPath,
                    parameters: {}
                };
                query.parameters[me.searchParameterName] = me.searchKeyword;
                temp = await me.repository.find(query);
                me.list = temp;
            }, 500),
        }
    },
    methods: {
        async fillSelections(){
            var response = await this.repository.find(null);
            // 받아온 데이터가 배열인지 확인하고 처리
            this.list = Array.isArray(response) ? response : response.data || [];
        },
        select(val) {
            this.referenceValue = val;
            if (val) {
                var uriParts = val._links.self.href.split('/');
                var id = uriParts.pop();
                val[this.idField] = id
                val = Object.assign({}, val)
                
                this.$emit('input', val);
                this.$emit('selected', val)
            } else {
                this.$emit('input', null);
                this.$emit('selected', null)
            }
        },
        getItemText(item) {
            if (!item) return '';
            
            // nameField가 문자열인 경우
            if (typeof this.nameField === 'string') {
                var id = item[_links.self.href].split('/')
                id = id.pop()
                return id;
            }
            
            // nameField가 함수인 경우
            const excludedKeys = ['_links', 'index'];
            const filteredKeys = Object.keys(item).filter(key => {
                const valueType = typeof item[key];
                return !excludedKeys.includes(key) && 
                    key !== 'id' && 
                    valueType !== 'object' && 
                    valueType !== 'number';
            });
            
            if (Object.keys(item).length < 3 || filteredKeys.length === 0) {
                return item.id;
            }
            
            return item[filteredKeys[1]];
        }
    },
    
};
</script>
<style>
.my-combobox div {
    min-height: 24px !important;
}
</style>
