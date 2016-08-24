/**
 * 
 */
/* controller for register page */
app
		.controller(
				'registerController',
				function($scope, $http, $location,islogger) {
					// register function to register the user after validating
					// all fields
					$scope.register = function() {
						console.log("user register ");
						var firstName = $scope.user.firstname;
						var lastName = $scope.user.lastname;
						var username = $scope.user.username;
						var password = $scope.user.password;
						var designation = $scope.user.designation;
						var phone_no = $scope.user.phone_no;
						console
								.log(document.getElementById('phone_no').value.length)
						if (document.getElementById('phone_no').value.length == 10) {
							if (password.length == 8) {
								document.getElementById('pass').innerHTML = "";
								var user = {
									username : username,
									password : password,
									firstName : firstName,
									lastName : lastName,
									designation : designation,
									phone_no : phone_no
								};
								console.log(user);
								$http
										.post(
												'http://10.207.60.159:8181/RBSExercise/register',
												user)
										.success(
												function(data) {
													console
															.log("data from register"
																	+ data);
													console.log(data != null);
													if (data) {
														$scope.greeting = data;
														islogger.setProperty(false);
														console
																.log("if not null");
														$location
																.path("/login");
													}

													else {
														console.log("error");

														document
																.getElementById('uservalidate').innerHTML = "Username exists,please enter valid UserName";
													}
												});
							} else {
								document.getElementById('pass').innerHTML = "Password must be 8 digit in length";
							}
						} else {
							document.getElementById('phone').innerHTML = "Phone no must be 10 digit in length";
						}
					}

					// password check function to match password entered in
					// password and retypr passowrd field
					$scope.passwordcheck = function(password, retypepassword) {
						if (!(document.getElementById('password').value == document
								.getElementById('retypepassword').value)) {
							document.getElementById('retype').innerHTML = "Password does not match";
						} else {
							document.getElementById('retype').innerHTML = "";
						}

					}
					// validate function to validate the fields except username
					// for special characters
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
					// validate function to validate username for special
					// characters
					$scope.validateUsername = function(text1, errorText) {
						var Name = document.getElementById(text1).value;
						var NameError = document.getElementById(errorText);
						if (!(/[sa-z0-9]+$/.test(Name))) {

							document.getElementById(text1).value = "";
							NameError.innerHTML = "<p >Invalid characters!Alphabets,Numbers only</p>";
							document.getElementById(text1).focus();
						} else
							NameError.innerHTML = "";
					}

				});