function Hello($scope, $http) {

	var firstName="";
	var lastName="";
	var username="shikha";
	var password="1234";
	var designation="";
	var phone_no="";
	var user={firstName:firstName,lastName:lastName,username:username,password:password,designation:designation,phone_no:phone_no}
    $http.post('/login',user).
        success(function(data) {
        	console.log(data);
        	if(user!=null)
            $scope.greeting = data;
        	else
        	console.log("error");
        });
}