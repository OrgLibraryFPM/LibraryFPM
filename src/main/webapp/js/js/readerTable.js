
var entityName = "reader";
var pagerName = '#'+entityName+"Pager";

var gridR = jQuery("#"+entityName+"Table")
    gridR.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    colNames:['ID','Прізвище', 'Ім\'я','По-батькові'],
    colModel:[
        {name:'id',index:'id', width:100},
        {name:'lastName',index:'lastName', width:200, editable:true},
        {name:'firstName',index:'firstName', width:200, editable:true},
        {name:'middleName',index:'middleName', width:200, editable:true}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Читачі",
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid);
    }
});
gridR.jqGrid('navGrid',pagerName,
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
            toCenter("editmod", gridR);
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
            toCenter("editmod", gridR);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridR);
        }
    }
);

$(window).resize(function(){
    gridR.jqGrid('setGridHeight',$(window).innerHeight()-200);
});

