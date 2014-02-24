var entityName = "bookType";
var pagerName = '#'+entityName+"Pager";

var gridB = jQuery("#"+entityName+"Table").jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    colNames:['ID','ТИП'],
    colModel:[
        {name:'id',index:'id', width:150},
        {name:'type',index:'type', width:550, editable:true}
    ],
    rowNum:5,
    rowList:[5,10,20],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Типи книг",
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid);
    }
});
gridB.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    {
        url:"/rest/"+entityName+"/updateOne",
        closeAfterEdit: true,
        ajaxEditOptions: { contentType: "application/json"},

        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["id","type"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridB);
        }
    },
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["type"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridB);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($('#bookTypeTable').jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridB);
        }
    }
);

$(window).resize(function(){
    gridB.jqGrid('setGridHeight',$(window).innerHeight()-200);
});
