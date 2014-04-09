
var entityName = "issuanse";
var pagerName = '#'+entityName+"Pager";

var gridIssuanse = jQuery("#"+entityName+"Table");

function splitReadersId(readerIds){
    var ids = split(readerIds);
    if (ids[ids.length-1].length==0){
        ids.pop();
    }
    var readers=[];
    jQuery.each(ids, function(i,val){
        readers[i] = {id:val};
    });
    return  readers;
}

//форма для редагування видавництва
var editWindowBook =  {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},
    recreateForm: true,
    resize:false,
    zIndex:99,
    serializeEditData : function(postdata, formid) {

        return (JSON.stringify({id:postdata.id,
                                name:postdata.name,
                                year:postdata.year,
                                isbn:postdata.isbn,
                                note:postdata.note,
                                bookType:{id:postdata.bookTypeId},
                                publication: {id:postdata.publicationId},
                                authors: splitReadersId(postdata.authorIds)
                               }));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridIssuanse);
    }
};

//формат виводу книг у комірку таблиці
function booksFormatter ( cellvalue, options, rowObject )
{
    var res = "";
    if (cellvalue && cellvalue.length){
        jQuery.each(cellvalue, function(i,val){
            res+=val.name+", ";
        });
    }
    return res.substring(0, res.length - 2);
}

//формат виводу читача у комірку таблиці
function readerFormatter ( cellvalue, options, rowObject ){
    return cellvalue.lastName+" "+cellvalue.firstName+" "+cellvalue.middleName;
}

//формат виводу видавництв у комірку таблиці
function publicationFormatter ( cellvalue, options, rowObject ){
    return cellvalue.name;
}

function publicationFormatterId ( cellvalue, options, rowObject ){
    return rowObject.publication.id;
}

function readerFormatterId ( cellvalue, options, rowObject ){
    return rowObject.reader.id;
}

function readersFormatterId ( cellvalue, options, rowObject ){
    var res = "";
    if (rowObject.authors && rowObject.authors.length){
        jQuery.each(rowObject.authors, function(i,val){
            res+=val.id+",";
        });
    }
    return res;
};

var bookTypeToAutocomplete = function (item) {
    return {
        id: item.id,
        label: item.type,
        value: item.type
    }
};

var publicationToAutocomplete = function (item) {
    return {
        id: item.id,
        label: item.name,
        value: item.name
    }
};

var readerToAutocomplete = function (item) {
    var reader = item.lastName+" "+item.firstName+" "+item.middleName;
    return {
        id: item.id,
        label: reader,
        value: reader
    }
};

gridIssuanse.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    width:700,
    colNames:['ID','Читач','', 'Дата видачі','Дата повернення','Книги',''],
    colModel:[
        {name:'id',index:'id', width:20},
        {name:'reader',index:'reader', width:200, editable:true, formatter:readerFormatter,
            editoptions:{
                        dataInit: function (elem) {
                                    autocomplete(elem, "/rest/reader/list", "readerId",readerToAutocomplete);
                                }
            }
        },
        {name:'readerId', index:'readerId', hidden:true, editable:true, formatter:readerFormatterId},
        {name:'dateIssuanse',index:'dateIssuanse', width:75, editable:true, sorttype:"date", formatter:dateFormatter},
        {name:'dateReturn',index:'dateReturn', width:75, editable:true, sorttype:"date" , formatter:dateFormatter},
        {name:'books',index:'books', width:329, editable:true, formatter:booksFormatter, edittype: "textarea",
            editoptions:{
                rows:4,
                cols:25,
                dataInit: function (elem) {
                    autocompleteMultiple(elem, "/rest/reader/list", "readerIds",authorToAutocomplete);
                }
            }
        },
        {name:'readerIds', index:'readerIds', hidden:true, editable:true, formatter:readersFormatterId}
    ],
    rowNum:10,
    rowList:[10,20,30],
    pager: pagerName,
    sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
    caption:"Видача",
    height: tableHeight,
    autowidth:false,
    shrinkToFit:false,
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid,editWindowBook);
    }
});
gridIssuanse.jqGrid('navGrid',pagerName,
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
        zIndex:99,
        resize:false,
        recreateForm: true,
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify({
                reader:{id:postdata.readerId},
                dateIssuanse:postdata.dateIssuanse,
                dateReturn:postdata.dateReturn,
                books: splitReadersId(postdata.readerIds)
            }));
        },

        beforeShowForm:function(form){
            toCenter("editmod", gridIssuanse);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify($("#"+entityName+"Table").jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridIssuanse);
        }
    }
);

$(window).resize(function(){
    gridIssuanse.jqGrid('setGridHeight', $(window).innerHeight()-200);
});

