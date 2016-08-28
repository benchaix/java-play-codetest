customerApp.controller('CustomerController', function CustomerController($scope, $http) {
	
  $scope.showContent = function($data){
	  $scope.customer = $data;
	  $scope.sortedCustomer = [];
	  $http({
		 method: 'POST',
		 url: 'http://localhost:9000/customers/sort',
		 data: $data
	  }).then(function successCallback(response) {
		  $scope.sortedCustomer = response.data;
	  }, function errorCallback(response) {
		  console.log(response.data);
	  });
  };
});

customerApp.directive('onReadFile', function ($parse) {
	return {
		restrict: 'A',
		scope: false,
		link: function(scope, element, attrs) {
            var fn = $parse(attrs.onReadFile);
            
			element.on('change', function(onChangeEvent) {
				var reader = new FileReader();
                
				reader.onload = function(onLoadEvent) {
					scope.$apply(function() {
						fn(scope, {$data:onLoadEvent.target.result});
					});
				};

				reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
			});
		}
	};
});