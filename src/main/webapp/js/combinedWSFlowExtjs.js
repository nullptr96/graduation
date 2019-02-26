/*
This file is part of Ext JS 3.4

Copyright (c) 2011-2013 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as
published by the Free Software Foundation and appearing in the file LICENSE included in the
packaging of this file.

Please review the following information to ensure the GNU General Public License version 3.0
requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department
at http://www.sencha.com/contact.

Build date: 2013-04-03 15:07:25
*/
Ext.onReady(function(){
    Ext.QuickTips.init();

    var comboData= [['类型1', '0'], ['类型2', '1']];
    var _getWSFunctionCombox = function ( id, comboData) {
        var combStore =  new Ext.data.SimpleStore({
            fields : ['showText', 'value'],
        });
        var comb = new Ext.form.ComboBox({
            id:id,
            mode : 'local',//本地数据
            store : combStore,
            width : "100%",
            displayField : 'showText',
            valueField : 'value',
            fieldLabel : '服务方法',
            _loadStore : function (comboData) {
                this.store.loadData(comboData)
            }
        });
        // comb.render(renderTo);
        comb._loadStore(comboData);
        this._getWSFunctionCombox = function(){
            return Ext.getCmp(id);
        }
        return comb;
    }

    // _getWSFunctionCombox('WSFunctionComb','nodeAttribute-wsfunction',comboData);


    var inputParamsData = [
        ['param1','string', 'a'],
        ['param2','string', 'b'],
        ['param3','string', 'c'],
    ];

    var outputParamsData = [
        ['param4','string', 'a'],
        ['param5','string', 'b'],
        ['param6','string', 'c'],
    ];


    var _getParamsGrid =function(id, title,storeData) {
        // create the data store
        var store = new Ext.data.ArrayStore({
            fields: [
                {name: 'name', type:'String'},
                {name: 'type',      type:'String'},
                {name: 'value',     type:'String'},
            ]
        });
        // create the Grid
        var grid = new Ext.grid.EditorGridPanel({
            id: id,
            store: store,
            columns: [
                {
                    id: 'name',
                    header: 'Name',
                    width: 100,
                    sortable: true,
                    dataIndex: 'name'
                },
                {
                    id: 'type',
                    header: 'Type',
                    width: 100,
                    sortable: true,
                    dataIndex: 'type'
                },
                {
                    id: 'value',
                    header: 'Name',
                    width: 100,
                    sortable: true,
                    dataIndex: 'value',
                    editor: new Ext.form.TextField({
                        allowBlank: false
                    })
                }
            ],
            stripeRows: true,
            autoExpandColumn: 'name',
            height: 180,
            width: 360,
            title: title,
            stateful: true,
            _loadStore : function (storeData) {
                this.store.loadData(storeData)
            }
        });
        // grid.render(renderTo);
        grid._loadStore(storeData)

        this._getParamsGrid = function(){
            return Ext.getCmp(id);
        }

        return grid;
    }
    //
    // var grid1 = new _getParamsGrid('inputParamsGrid','inputParamsDiv',inputParamsData );
    // var grid2 = new _getParamsGrid('inputParamsGrid','outputParamsDiv',outputParamsData );

    var _getAttributePanel = function(){
        var id = 'attributePanel';
        var panel = new Ext.FormPanel({
            title: '属性面板',
            height: 500,
            id: id,
            items:[
                {
                    id: 'flowDesc',
                    xtype : 'textfield',
                    fieldLabel: '流程描述',
                    height: 60,
                    width: "80%",
                    hidden:false,
                    hideLabel:false,
                    readonly:true
                },{
                    id: 'WSid',
                    xtype : 'textfield',
                    fieldLabel: '服务id',
                    width: "80%",
                    hidden:false,
                    hideLabel:false,
                    readonly:true
                }, {
                    id: 'WSName',
                    xtype : 'textfield',
                    fieldLabel: '服务中文名',
                    width: "80%",
                    hidden:false,
                    hideLabel:false,
                    readonly:true
                },
                new _getWSFunctionCombox('WSFunctionComb',comboData),
                new _getParamsGrid('inputParamsGrid', '输入参数',inputParamsData),
                new _getParamsGrid('outputParamsGrid','输出参数',outputParamsData)
            ],
            // renderTo:'attributeDiv'
        })
        panel.render('attributePanelDiv');
        this._getAttributePanel = function(){
            return Ext.getCmp(id);
        }
        return panel;
    }

    new _getAttributePanel();
});


// /**
//  * Custom function used for column renderer
//  * @param {Object} val
//  */
// function change(val) {
//     if (val > 0) {
//         return '<span style="color:green;">' + val + '</span>';
//     } else if (val < 0) {
//         return '<span style="color:red;">' + val + '</span>';
//     }
//     return val;
// }
//
// /**
//  * Custom function used for column renderer
//  * @param {Object} val
//  */
// function pctChange(val) {
//     if (val > 0) {
//         return '<span style="color:green;">' + val + '%</span>';
//     } else if (val < 0) {
//         return '<span style="color:red;">' + val + '%</span>';
//     }
//     return val;
// }
