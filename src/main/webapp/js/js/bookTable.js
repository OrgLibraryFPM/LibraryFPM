
var entityName = "book";
var pagerName = '#'+entityName+"Pager";

var gridBook = jQuery("#"+entityName+"Table");

var editWindowBook =  {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},
    recreateForm: true,
    serializeEditData : function(postdata, formid) {
        return (JSON.stringify({id:postdata.id,
                                name:postdata.name,
                                year:postdata.year,
                                isbn:postdata.isbn,
                                note:postdata.note,
                                bookType:{id:postdata['bookType.type']},
                                publication: {id:postdata['publication.name']}
                               }));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridBook);
    }
};

var selectBookType = function(response){
    var data = $.parseJSON(response);
    var res = "<select>";
    if (data && data.length) {
        jQuery.each(data, function(i,val){
            res +='<option value="'+val.id+'">'+val.type+'</option>';
        });
    }
    return res + "</select>";
};

var selectPublication = function(response){
    var data = $.parseJSON(response);
    var res = "<select>";
    if (data && data.length) {
        jQuery.each(data, function(i,val){
            res +='<option value="'+val.id+'">'+val.name+'</option>';
        });
    }
    return res + "</select>";
};

gridBook.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    colNames:['ID','Назва', 'Рік','ISBN','Опис','Тип','Видання'],
    colModel:[
        {name:'id',index:'id', width:50},
        {name:'name',index:'name', width:120, editable:true},
        {name:'year',index:'year', width:50, editable:true},
        {name:'isbn',index:'isbn', width:100, editable:true},
        {name:'note',index:'note', width:180, editable:true},
        {name:'bookType.type',index:'bookType', width:100, editable:true, edittype:'select',
            editoptions:{
                ajaxSelectOptions:{cache: false},
                dataUrl:"/rest/bookType/list",
                buildSelect: selectBookType
            }
        },
        {name:'publication.name',index:'publication', width:100, editable:true, edittype:'select',
            editoptions:{
                ajaxSelectOptions:{cache: false},
                dataUrl:"/rest/publication/list",
                buildSelect: selectPublication
            }
        }
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видання",
    height: tableHeight,
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid,editWindowBook);
    }
});
gridBook.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true
    },
    editWindowBook,
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify({
                name:postdata.name,
                year:postdata.year,
                isbn:postdata.isbn,
                note:postdata.note,
                bookType:{id:postdata['bookType.type']},
                publication: {id:postdata['publication.name']}
            }));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridBook);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridBook);
        }
    }
);

$(window).resize(function(){
    gridBook.jqGrid('setGridHeight', $(window).innerHeight()-200);
});

