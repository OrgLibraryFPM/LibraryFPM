var entityName = "bookType";
var pagerName = '#'+entityName+"Pager";

var gridB = jQuery("#"+entityName+"Table");

var editWindowB = {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: {contentType: "application/json"},

    serializeEditData : function(postdata, formid) {
        return (JSON.stringify(postdata, ["id","type"]));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridB);
    }
};

gridB.jqGrid({
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
    height:tableHeight,
    ondblClickRow: function(rowid) {
        gridB.jqGrid('editGridRow', rowid,editWindowB);
    }
});

gridB.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    editWindowB,
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
