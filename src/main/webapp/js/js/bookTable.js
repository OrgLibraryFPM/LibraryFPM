
var entityName = "book";
var pagerName = '#'+entityName+"Pager";

var gridBook = jQuery("#"+entityName+"Table");

//форма для редагування видавництва
var editWindowBook =  {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},
    recreateForm: true,
    serializeEditData : function(postdata, formid) {
        console.log(postdata);
        return (JSON.stringify({id:postdata.id,
                                name:postdata.name,
                                year:postdata.year,
                                isbn:postdata.isbn,
                                note:postdata.note,
                                bookType:{id:postdata.bookType},
                                publication: {id:postdata.publication}
                               }));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridBook);
    }
};

//випадаючий список типів книжок
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

//випадаючий список видавництв
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

//формат виводу авторів у комірку таблиці
function authorsFormatter ( cellvalue, options, rowObject )
{
    var res = "";
    if (cellvalue && cellvalue.length){
        jQuery.each(cellvalue, function(i,val){
            res+=val.lastName+" "+val.firstName+" "+val.middleName+", ";
        });
    }
    return res.substring(0, res.length - 2);
}

//формат виводу типів видавнь у комірку таблиці
function bookTypeFormatter ( cellvalue, options, rowObject ){
    return cellvalue.type;
}

//формат виводу видавництв у комірку таблиці
function publicationFormatter ( cellvalue, options, rowObject ){
    return cellvalue.name;
}

gridBook.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    width:700,
    colNames:['ID','Назва', 'Рік','ISBN','Опис','Тип','Видання','Автори'],
    colModel:[
        {name:'id',index:'id', width:50},
        {name:'name',index:'name', width:200, editable:true},
        {name:'year',index:'year', width:50, editable:true},
        {name:'isbn',index:'isbn', width:100, editable:true},
        {name:'note',index:'note', width:180, editable:true},
        {name:'bookType',index:'bookType', width:100, editable:true, edittype:'select',
            editoptions:{
                ajaxSelectOptions:{cache: false},
                dataUrl:"/rest/bookType/list",
                buildSelect: selectBookType
            },
            formatter:bookTypeFormatter
        },
        {name:'publication',index:'publication', width:100, editable:true, edittype:'select',
            editoptions:{
                ajaxSelectOptions:{cache: false},
                dataUrl:"/rest/publication/list",
                buildSelect: selectPublication
            },
            formatter: publicationFormatter
        },
        {name:'authors', index:'authors',width:200,editable:false,formatter:authorsFormatter}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видання",
    height: tableHeight,
    autowidth:false,
    shrinkToFit:false,
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
                bookType:{id:postdata.bookType},
                publication: {id:postdata.publication}
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

