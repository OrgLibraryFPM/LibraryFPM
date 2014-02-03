Lib.Collection.BookTypes = Backbone.Collection.extend({
	model: Lib.Model.BookType,
	
	url: 'rest/bookType/all'
});