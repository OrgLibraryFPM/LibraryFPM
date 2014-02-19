
jQuery("#publicationTable").jqGrid({
    url:'/rest/publication/all',
    datatype: "json",
    colNames:['ID','Назва видавництва', 'Місто'],
    colModel:[
        {name:'id',index:'id', width:100},
        {name:'name',index:'name', width:300, editable:true},
        {name:'city',index:'city', width:300, editable:true}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: '#pagerPublication',
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видавництва",
    height: 500
});
jQuery("#publicationTable").jqGrid('navGrid','#pagerPublication',
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    {
        url:"/rest/publication/updateOne",
        closeAfterEdit: true,
        ajaxEditOptions: { contentType: "application/json"},

        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["id","name","city"]));
        }
    },
    {
        url:"/rest/publication/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify(postdata, ["name","city"]));
        }
    },
    {
        url:"/rest/publication/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($('#publicationTable').jqGrid('getGridParam', 'selrow')));
        }
    }
);

$(window).resize(function(){
    $('#publicationTable').jqGrid('setGridHeight',$(window).innerHeight()-200);
});