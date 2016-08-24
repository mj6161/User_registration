/**
 * 
 */
app.factory('sessionService', [ '$http', function($http) {
	return {
		set : function(key, value) {
			return sessionStorage.setItem(key, value);
		},
		get : function(key) {
			return sessionStorage.getItem(key);
		},
		destroy : function(key) {
			console.log(key);
			sessionStorage.clear();
			console.log("removed");

		}

	};
} ]);
