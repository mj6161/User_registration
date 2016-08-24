/**
 * 
 */
/* Controller for home page */
app
		.controller(
				'homeController',
				function($scope, $http, editService, $location, loginService,
						sessionService, islogger) {

					$scope.userData = editService.getProperty();
					// Update user function
					$scope.updateUser = function() {
						console.log("updating");

						var firstName = $scope.userData.firstName;
						var lastName = $scope.userData.lastName;
						var username = $scope.userData.username;
						var password = $scope.user.password;
						var designation = $scope.user.designation;
						var phone_no = $scope.user.phone_no;

						if (password.length == 8) {
							var user = {
								username : username,
								password : password,
								firstName : firstName,
								lastName : lastName,
								designation : designation,
								phone_no : phone_no
							};
							console.log(user);
							// post request sent to server side to update user
							$http
									.post(
											'http://10.207.60.159:8181/RBSExercise/edit',
											user)
									.success(
											function(msg) {
												if (msg) {
													console.log(msg);
													editService
															.setProperty(msg);
													$scope.userData = editService
															.getProperty();
													$location.path('/home');
													$scope.msgtxterror = "Successfully Updated";
													document
															.getElementById('pass').innerHTML = "";
												}

												else {

													$location.path('/home');
													console.log("error");
													$scope.msgtxterror = "Error in updating";
												}
											});
						} else {
							document.getElementById('pass').innerHTML = "Password must be 8 digit in length";
						}

					}
					// logout function to destroy sessions
					$scope.logout = function() {
						/* loginService.logout(); */
						var user = sessionService.get('user');
						console.log("username in logout" + user);
						$http.post(
								'http://10.207.60.159:8181/RBSExercise/logout',
								user).success(
								function(msg) {
									console.log("form logout" + msg);
									if (msg) {
										editService.setProperty("");
										sessionService.destroy(sessionService
												.get('user'));
										console.log("in logout");
										islogger.setProperty(false);
										$location.path('/login');
									} else {
										console.log("not logout");
									}
								});

					}
					// edit user function to edit the fields in home page
					$scope.editUser = function() {
						console.log("editing");
						document.getElementById("designation").readOnly = false;
						document.getElementById("designation").style.border = "thick solid #BABABA";
						document.getElementById("phone_no").readOnly = false;
						document.getElementById("phone_no").style.border = "thick solid #BABABA";
						document.getElementById("password").readOnly = false;
						document.getElementById("password").style.border = "thick solid #BABABA";
					}
					// validate function to validate designation field
					$scope.validate = function(text1, errorText) {
						var Name = document.getElementById(text1).value;
						var NameError = document.getElementById(errorText);
						if (!(/[ a-zA-Z]+$/.test(Name))) {

							document.getElementById(text1).value = "";
							NameError.innerHTML = "<p >Invalid character!Alphabets only</p>";
							document.getElementById(text1).focus();
						} else
							NameError.innerHTML = "";
					}

				});