//(function() {
//	window.Lib = {
//		Model : {},
//		Collection : {},
//		View : {},
//		Router : {}
//	}
//
//	Lib.runTemplate = function(idTemlate) {
//		return _.template($('#' + idTemlate).html());
//	}
//
//
//}());

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
}