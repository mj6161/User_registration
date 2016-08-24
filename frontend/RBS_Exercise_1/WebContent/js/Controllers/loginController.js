/*
 * 
 */
/* Controller for login*/
app.controller('loginController', function($scope, $http, $location,
		sessionService, editService, islogger) {
	/* login function */
	$scope.registerpage = function() {
		islogger.setProperty(true);
		$location.path("/register");
	}
	$scope.login = function() {
		var firstName = 'null';
		var lastName = 'null';
		var username = $scope.user.username;
		var password = $scope.user.password;
		var designation = 'null';
		var phone_no = '0';
		var user = {
			username : username,
			password : password,
			firstName : firstName,
			lastName : lastName,
			designation : designation,
			phone_no : phone_no
		};
		console.log(user);
		// User sent to server side for authentication*/
		$http.post('http://10.207.60.159:8181/RBSExercise/login', user)
				.success(function(msg) {
					if (msg) {
						console.log(msg);
						$scope.msgtxterror = "Correct Information";

						editService.setProperty(msg);

						sessionService.set('user', msg.username);
						console.log(sessionService.get('user'));
						islogger.setProperty(true);
						$location.path('/home');

					}

					else {
						var msg = null;
						editService.setProperty(msg);
						sessionService.set('user', null);
						$location.path('/login');
						console.log("error");
						$scope.msgtxterror = "Incorrect Credentials";
					}
				});
	}

});