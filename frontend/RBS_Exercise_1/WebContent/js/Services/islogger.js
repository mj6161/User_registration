/**
 * 
 */
app.service('islogger', function() {
	var property = false;

	return {
		getProperty : function() {
			return property;
		},
		setProperty : function(value) {
			property = value;
		}
	};
});