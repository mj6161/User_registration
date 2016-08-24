/**
 * 
 */
app.factory('loginService', function($http, sessionService, islogger) {
	return {

		islogged : function() {
			console.log("inside islogged function");
			/*
			 * var user = sessionService.get('user'); console.log("in islogged
			 * function" + user);
			 */
			$http.get('http://10.207.60.159:8181/RBSExercise/checklogin')
					.success(function(msg) {
						if (msg) {
							console.log(msg != null);
							console.log("iside islogged" + msg);
							islogger.setProperty(true);

						}
					});

		}

	};
});