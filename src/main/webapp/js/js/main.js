(function() {
	window.Lib = {
		Model : {},
		Collection : {},
		View : {},
		Router : {}
	}

	Lib.runTemplate = function(idTemlate) {
		return _.template($('#' + idTemlate).html());
	}
}());