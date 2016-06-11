app.controller('AccController', ['$scope', 'accounts', function($scope) {
  accounts.success(function(data){
		$scope.accounts = data;
	});
}]);
