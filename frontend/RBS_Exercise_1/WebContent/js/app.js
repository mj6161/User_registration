/**
 * 
 */
var app = angular.module("LoginApp", [ "ngRoute" ]);
app.config([ "$routeProvider", function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'login.tpl.html',
		controller : 'loginController'
	}).when('/home', {
		templateUrl : 'home.html',
		controller : 'homeController'
	}).when('/register', {
		templateUrl : 'register.html',
		controller : 'registerController'
	}).otherwise({
		redirectTo : '/login'
	});
} ]);
app.run(function($rootScope, $location, loginService, islogger) {
	var routespermission = [ '/home' ];
	var routesp = [ '/register' ];
	$rootScope.$on('$routeChangeStart', function() {

		loginService.islogged();
		console.log("if login" + islogger.getProperty());
		if (!(islogger.getProperty()))

		{

			$location.path("/login");
			/*
			 * if (routesp.indexOf($location.path() == -1)) {
			 * $location.path("/register"); }
			 */

		}

	});
});
