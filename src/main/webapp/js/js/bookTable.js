
var entityName = "book";
var pagerName = '#'+entityName+"Pager";

var gridA = jQuery("#"+entityName+"Table")
    gridA.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    colNames:['ID','Назва', 'Рік','ISBN','Опис','Тип'],
    colModel:[
        {name:'id',index:'id', width:100},
        {name:'name',index:'name', width:120, editable:true},
        {name:'year',index:'year', width:100, editable:true},
        {name:'isbn',index:'isbn', width:100, editable:true},
        {name:'note',index:'note', width:180, editable:true},
        {name:'bookType.type',index:'bookType', width:100, editable:true}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видання",
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid);
    }
});
gridA.jqGrid('navGrid',pagerName,
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
            return (JSON.stringify(postdata, ["id","lastName","firstName", "middleName"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridA);
        }
    },
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["lastName","firstName", "middleName"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridA);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridA);
        }
    }
);

$(window).resize(function(){
    gridA.jqGrid('setGridHeight',$(window).innerHeight()-200);
});

