
var entityName = "reader";
var pagerName = '#'+entityName+"Pager";

var gridReader = jQuery("#"+entityName+"Table")
var editWindowR = {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},

    serializeEditData : function(postdata, formid) {
        return (JSON.stringify(postdata, ["id","lastName","firstName", "middleName"]));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridReader);
    }
};
    gridReader.jqGrid({
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
    height:tableHeight,
    toppager: true,
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid,editWindowR);
    }
});
gridReader.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true,
        cloneToTop:true
    },
    editWindowR,
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["lastName","firstName", "middleName"]));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridReader);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridReader);
        }
    }
);

editingButtonToTop(gridReader, pagerName);

$(window).resize(function(){
    gridReader.jqGrid('setGridHeight',$(window).innerHeight()-200);
});

