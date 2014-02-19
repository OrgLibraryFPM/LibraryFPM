
jQuery("#bookTypeTable").jqGrid({
    url:'/rest/bookType/all',
    datatype: "json",
    colNames:['ID','ТИП'],
    colModel:[
        {name:'id',index:'id', width:150},
        {name:'type',index:'type', width:550, editable:true}
    ],
    rowNum:5,
    rowList:[5,10,20],
    pager: '#pagerBookType',
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Типи книг"
});
jQuery("#bookTypeTable").jqGrid('navGrid','#pagerBookType',
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    {
        url:"/rest/bookType/updateOne",
        closeAfterEdit: true,
        ajaxEditOptions: { contentType: "application/json"},

        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["id","type"]));
        }
    },
    {
        url:"/rest/bookType/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["type"]));
        }
    },
    {
        url:"/rest/bookType/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($('#bookTypeTable').jqGrid('getGridParam', 'selrow')));
        }
    }
);

$(window).resize(function(){
    $('#bookTypeTable').jqGrid('setGridHeight',$(window).innerHeight()-200);
});
