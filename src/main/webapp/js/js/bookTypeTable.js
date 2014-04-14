var entityName = "bookType";
var pagerName = '#'+entityName+"Pager";

var gridBookType = jQuery("#"+entityName+"Table");

var editWindowB = {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: {contentType: "application/json"},

    serializeEditData : function(postdata, formid) {
        return (JSON.stringify(postdata, ["id","type"]));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridBookType);
    }
};

gridBookType.jqGrid({
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
    toppager: true,
    ondblClickRow: function(rowid) {
        gridBookType.jqGrid('editGridRow', rowid,editWindowB);
    }
});

gridBookType.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true,
        cloneToTop:true
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
            toCenter("editmod", gridBookType);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($('#bookTypeTable').jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridBookType);
        }
    }
);

editingButtonToTop(gridBookType, pagerName);

$(window).resize(function(){
    gridBookType.jqGrid('setGridHeight',$(window).innerHeight()-200);
});
