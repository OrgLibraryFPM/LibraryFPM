Lib.View.BookTypeApp = Backbone.View.extend({
	initialize: function() {
		var allBookTypesView = new Lib.View.BookTypes({ collection: Lib.bookTypes }).render();
        $('#allBookTypes').append( allBookTypesView.el );
	}
});

Lib.View.BookType = Backbone.View.extend({
	tagName : 'tr',

	template : Lib.runTemplate('bookTypeTmp'),

	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});

Lib.View.BookTypes = Backbone.View.extend({
	tagName : 'tbody',

	render : function() {
		this.collection.each(this.addOne, this);
		return this;
	},

	addOne : function(bookType) {
		var bookTypeView = new Lib.View.BookType({
			model : bookType
		});
		this.$el.append(bookTypeView.render().el);
	}
});