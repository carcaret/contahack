app.factory('accounts', ['$http', function($http) {
  var req = {
   method: 'GET',
   url: 'https://apisandbox.openbankproject.com/obp/v2.0.0/accounts',
   headers: {
     'Content-Type': 'application/json',
     'Authorization': 'DirectLogin token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.l6eRf9LrCkOyOl-EqlQMMLlVqWolTcuBLMIFf59oxWE"'
   }
  }
  return $http(req)
    .success(function(data) {
      return data;
    })
    .error(function(err) {
      return err;
    });
}]);
