var entityName = "publication";
var pagerName = '#'+entityName+"Pager";

var gridP = jQuery("#"+entityName+"Table");

var editWindowP = {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},

    serializeEditData : function(postdata, formid) {
        return (JSON.stringify(postdata, ["id","name","city"]));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridP);
    }
};

    gridP.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    colNames:['ID','Назва видавництва', 'Місто'],
    colModel:[
        {name:'id',index:'id', width:100},
        {name:'name',index:'name', width:300, editable:true},
        {name:'city',index:'city', width:300, editable:true}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видавництва",
    height:tableHeight,
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid, editWindowP);
    }
});
gridP.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    editWindowP,
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["name","city"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridP);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridP);
        }
    }
);

$(window).resize(function(){
    gridP.jqGrid('setGridHeight',$(window).innerHeight()-200);
});

