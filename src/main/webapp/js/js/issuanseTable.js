
var entityName = "issuanse";
var pagerName = '#'+entityName+"Pager";

var gridIssuanse = jQuery("#"+entityName+"Table");

function splitBooksId(bookIds){
    var ids = split(bookIds);
    if (ids[ids.length-1].length==0){
        ids.pop();
    }
    var books=[];
    jQuery.each(ids, function(i,val){
        books[i] = {id:val};
    });
    return  books;
}

//форма для редагування видавництва
var editWindowIssuanse =  {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},
    recreateForm: true,
    resize:false,
    zIndex:99,
    serializeEditData : function(postdata, formid) {
        var dateSplit = postdata.dateIssuanse.split(".");
        var dateIssuanse = new Date(dateSplit[2], parseInt(dateSplit[1])-1,dateSplit[0]).getTime();
        var dateReturn = null;
        if (postdata.dateReturn="true"){
            dateReturn = new Date().getTime();
        }
        return (JSON.stringify({
            id: postdata.id,
            reader:{id:postdata.readerId},
            dateIssuanse:dateIssuanse,
            dateReturn:dateReturn,
            books: splitBooksId(postdata.bookIds)
        }));
    },

    beforeShowForm:function(form){
        $('#tr_dateIssuanse', form).hide();
        var  selRowId = gridIssuanse.jqGrid ('getGridParam', 'selrow');
        var celValue = gridIssuanse.jqGrid ('getCell', selRowId, 'dateReturn');
        if (celValue.length>0){
            var b =$('#dateReturn', form);
            b.prop('checked',true);
        }
        $("#tr_dateReturn").find("td.CaptionTD").text("Повернуто");
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

function readerFormatterId ( cellvalue, options, rowObject ){
    return rowObject.reader.id;
}

function booksFormatterId ( cellvalue, options, rowObject ){
    var res = "";
    if (rowObject.books && rowObject.books.length){
        jQuery.each(rowObject.books, function(i,val){
            res+=val.id+",";
        });
    }
    return res;
};

var bookToAutocomplete = function (item) {
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
        {name:'dateReturn',index:'dateReturn', width:75, editable:true, edittype: "checkbox", editoptions: {value: "true:false"}, sorttype:"date" , formatter:dateFormatter},
        {name:'books',index:'books', width:329, editable:true, formatter:booksFormatter, edittype: "textarea",
            editoptions:{
                rows:4,
                cols:25,
                dataInit: function (elem) {
                    autocompleteMultiple(elem, "/rest/book/list", "bookIds",bookToAutocomplete);
                }
            }
        },
        {name:'bookIds', index:'bookIds', hidden:true, editable:true, formatter:booksFormatterId}
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
    toppager: true,
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid,editWindowIssuanse);
    }
});
gridIssuanse.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true,
        cloneToTop:true
    },
    editWindowIssuanse,
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        zIndex:99,
        resize:false,
        recreateForm: true,
        serializeEditData : function(postdata, formid) {
            var dateIssuanse = new Date().getTime();
            var dateReturn = null;
            return (JSON.stringify({
                reader:{id:postdata.readerId},
                dateIssuanse:dateIssuanse,
                dateReturn:dateReturn,
                books: splitBooksId(postdata.bookIds)
            }));
        },

        beforeShowForm:function(form){
            $('#tr_dateIssuanse', form).hide();
            $('#tr_dateReturn', form).hide();
            toCenter("editmod", gridIssuanse);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            var value = gridIssuanse.jqGrid('getGridParam', 'selrow');
            return (JSON.stringify(value));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridIssuanse);
        }
    }
);
editingButtonToTop(gridIssuanse, pagerName);

$(window).resize(function(){
    gridIssuanse.jqGrid('setGridHeight', $(window).innerHeight()-200);
});

