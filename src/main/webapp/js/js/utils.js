
var tableHeight = $(window).innerHeight()-200;

// вирівнює діалогові вікна jqGrid по-центру
function toCenter(name, grid){

    var dlgDiv = $("#"+name + grid[0].id);
    var parentDiv = dlgDiv.parent(); // div#gbox_list

    var dlgWidth = dlgDiv.width();
    var parentWidth = parentDiv.width();
    var dlgHeight = dlgDiv.height();
    var parentHeight = parentDiv.height();
    var parentTop = parentDiv.offset().top;
    var parentLeft = parentDiv.offset().left;


    // HINT: change parentWidth and parentHeight in case of the grid
    //       is larger as the browser window
    dlgDiv[0].style.top =  parentTop + 55 + "px";
    dlgDiv[0].style.left = Math.round(  parentLeft + (parentWidth-dlgWidth)/2 )  + "px";
};

//Форматер для виводу дати
function dateFormatter (cellval, opts) {
    if (cellval==null){
        return "";
    }
    var date = new Date(cellval);
    opts = $.extend({}, $.jgrid.formatter.date, opts);
    return $.datepicker.formatDate('dd.mm.yy', date);
}

// виділення шуканого фрагменту в авто підказці
function highlightText(text, $node) {
    var searchText = $.trim(text).toLowerCase(), currentNode = $node.get(0).firstChild, matchIndex, newTextNode, newSpanNode;
    if(searchText.length>0){
        while ((matchIndex = currentNode.data.toLowerCase().indexOf(searchText)) >= 0) {
            newTextNode = currentNode.splitText(matchIndex);
            currentNode = newTextNode.splitText(searchText.length);
            newSpanNode = document.createElement("span");
            newSpanNode.className = "highlight";
            currentNode.parentNode.insertBefore(newSpanNode, currentNode);
            newSpanNode.appendChild(newTextNode);
        }
    }
};

// авто підказки
function autocomplete(elem, url, fieldId, formatter) {

    setTimeout(function () {
        $(elem).autocomplete({
            source:function( request, response ) {
                $.getJSON(url, request,
                    function(data) {
                        response($.map(data, formatter));
                    });
            },
            minLength: 0,
            select: function (event, ui) {
                $(elem).val(ui.item.value);
                $("#"+fieldId).val(ui.item.id);
                $(elem).trigger('change');
            }
        }).focus(function(){
            $(this).autocomplete("search",'');
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            var $a = $("<a></a>").text(item.label);
            highlightText(this.term, $a);
            return $("<li></li>").append($a).appendTo(ul);
        };
    }, 50);
};


function split( val ) {
    return val.split( /,\s*/ );
}

function autocompleteTokenInput(elem, url, fieldId, dataInit, formatter){

    $(elem).tokenInput(url,{
        queryParam:"term",
        prePopulate: dataInit,
        onResult: function (results) {
            if (formatter !== undefined){
                var res = new Array();
                $.each(results,function(i,val){
                    res.push(formatter(val));
                });
                return res;
            }else{
                return results;
            }
        },
        onAdd: function(item){
            var Ids = $("#" + fieldId);
            Ids.val(Ids.val()+item.id+",");
        },
        onDelete: function(item){
            var Ids = $("#" + fieldId);
            Ids.val("");
            var tokenData = this.tokenInput("get");
            $.each(tokenData, function(i,val){
                Ids.val(Ids.val() + val.id + ",");
            });
        }
    });
}

function editingButtonToTop(grid,pagerName){
    var gridId = grid[0].id;
    var topPagerDiv = $('#' + gridId + '_toppager')[0];
    $("#"+ gridId+"_toppager_center", topPagerDiv).remove();
    $("#"+ gridId+"_toppager_right", topPagerDiv).remove();
    $("tbody",pagerName+"_left").remove();
}