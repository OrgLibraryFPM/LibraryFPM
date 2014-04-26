
var entityName = "book";
var pagerName = '#'+entityName+"Pager";

var gridBook = jQuery("#"+entityName+"Table");
var gridBooksData;

function splitAuthorsId(authorIds){
    var ids = split(authorIds);
    if (ids[ids.length-1].length==0){
        ids.pop();
    }
    var authors=[];
    jQuery.each(ids, function(i,val){
        authors[i] = {id:val};
    });
    return  authors;
}

//форма для редагування видавництва
var editWindowBook =  {
    url:"/rest/"+entityName+"/updateOne",
    closeAfterEdit: true,
    ajaxEditOptions: { contentType: "application/json"},
    recreateForm: true,
    resize:false,
    zIndex:99,
    width:400,
    serializeEditData : function(postdata, formid) {

        return (JSON.stringify({id:postdata.id,
                                name:postdata.name,
                                year:postdata.year,
                                isbn:postdata.isbn,
                                note:postdata.note,
                                tome:postdata.tome,
                                number:postdata.number,
                                part:postdata.part,
                                series:postdata.series,
                                pageCount:postdata.pageCount,
                                bookType:{id:postdata.bookTypeId},
                                publication: {id:postdata.publicationId},
                                authors: splitAuthorsId(postdata.authorIds)
       }));
    },

    beforeShowForm:function(form){
        toCenter("editmod", gridBook);
    }
};

function toUpperFirstSymwol(str){
    return str.charAt(0).toUpperCase();
}

var authorFormatView = function (val) {
    return val.lastName + " " + toUpperFirstSymwol(val.firstName) + ". " + toUpperFirstSymwol(val.middleName)+ ".";
}

//формат виводу авторів у комірку таблиці
function authorsFormatter ( cellvalue, options, rowObject )
{
    var res = "";
    if (cellvalue && cellvalue.length){
        jQuery.each(cellvalue, function(i,val){
            res+=authorFormatView(val)+"., ";
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

function publicationFormatterId ( cellvalue, options, rowObject ){
    return rowObject.publication.id;
}

function bookTypeFormatterId ( cellvalue, options, rowObject ){
    return rowObject.bookType.id;
}

function authorsFormatterId ( cellvalue, options, rowObject ){
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

var authorToAutocomplete = function (item) {
    var author = item.lastName+" "+item.firstName+" "+item.middleName;
    return {
        id: item.id,
        name: author
    }
};

gridBook.jqGrid({
    url:"/rest/"+entityName+"/all",
    datatype: "json",
    width:700,
    colNames:['ID','Назва', 'Рік','ISBN','Опис','Тип','','Видання','','Автори','','Том','Номер','Частина','Серія','К-сть сторінок'],
    colModel:[
        {name:'id',index:'id', width:50},
        {name:'name',index:'name', width:200, editable:true},
        {name:'year',index:'year', width:50, editable:true},
        {name:'isbn',index:'isbn', width:100, editable:true},
        {name:'note',index:'note', width:180, editable:true},
        {name:'bookType',index:'bookType', width:100, editable:true,
            editoptions:{
                dataInit: function (elem) {
                    autocomplete(elem, "/rest/bookType/list", "bookTypeId",bookTypeToAutocomplete);
                }
            },
            formatter:bookTypeFormatter
        },
        {name:'bookTypeId', index:'bookTypeId', hidden:true, editable:true, formatter:bookTypeFormatterId},
        {name:'publication',index:'publication', width:100, editable:true,
            editoptions:{
                dataInit: function (elem) {
                    autocomplete(elem, "/rest/publication/list", "publicationId",publicationToAutocomplete);
                }
            },
            formatter: publicationFormatter
        },
        {name:'publicationId', index:'publicationId', hidden:true, editable:true, formatter:publicationFormatterId},
        {name:'authors', index:'authors', width:200, editable:true,
            editoptions:{
                dataInit: function (elem) {
                    var authors = new Array();
                    if (gridBooksData!=null){
                        var sel_id = gridBook.jqGrid('getGridParam', 'selrow');
                        var data = $.grep(gridBooksData, function(el){
                            return el.id == sel_id;
                        });
                        $.each(data[0].authors, function(i,val){
                            authors.push(authorToAutocomplete(val));
                        });
                    }
                    autocompleteTokenInput(elem,"/rest/author/list", "authorIds", authors, authorToAutocomplete);
                }
            },
            formatter:authorsFormatter
        },
        {name:'authorIds', index:'authorIds', hidden:true, editable:true, formatter:authorsFormatterId},
        {name:'tome',index:'tome', width:50, editable:true},
        {name:'number',index:'number', width:50, editable:true},
        {name:'part',index:'part', width:50, editable:true},
        {name:'series',index:'series', width:50, editable:true},
        {name:'pageCount',index:'pageCount', width:50, editable:true},
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
    toppager: true,
    loadComplete:function(data){
      gridBooksData = data.rows;
    },
    ondblClickRow: function(rowid) {
        jQuery(this).jqGrid('editGridRow', rowid,editWindowBook);
    }
});
gridBook.jqGrid('navGrid',pagerName,
    {
        search:false,
        edit:true,
        add:true,
        del:true,
        cloneToTop:true
    },
    editWindowBook,
    {
        url:"/rest/"+entityName+"/createOne",
        closeAfterAdd: true,
        ajaxEditOptions: { contentType: "application/json"},
        zIndex:99,
        resize:false,
        recreateForm: true,
        width:400,
        serializeEditData : function(postdata, formid) {
            return (JSON.stringify({
                name:postdata.name,
                year:postdata.year,
                isbn:postdata.isbn,
                note:postdata.note,
                tome:postdata.tome,
                number:postdata.number,
                part:postdata.part,
                series:postdata.series,
                pageCount:postdata.pageCount,
                bookType:{id:postdata.bookTypeId},
                publication: {id:postdata.publicationId},
                authors: splitAuthorsId(postdata.authorIds)
            }));
        },
        beforeInitData : function(formid){
            gridBooksData = null;
        },
        beforeShowForm:function(form){
            toCenter("editmod", gridBook);
        }
    },
    {
        url:"/rest/"+entityName+"/del",
        ajaxDelOptions: { contentType: "application/json"},
        serializeDelData : function(postdata, formid) {
            return (JSON.stringify(gridBook.jqGrid('getGridParam', 'selrow')));
        },

        beforeShowForm:function(form){
            toCenter("delmod", gridBook);
        }
    }
);

editingButtonToTop(gridBook, pagerName);

$(window).resize(function(){
    gridBook.jqGrid('setGridHeight', $(window).innerHeight()-200);
});

