angular.module('app.services', [])
.service("GetHealthSystem", ['$http', function($http) {
    this.getHealthSystemByName = function getHealthSystemByName(name) {
        var query = '?name='+name;
        return $http({
            method: 'GET',
            url: 'healthsystem' + query
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
    this.getAllHealthSystems = function getAllHealthSystems() {
        return $http({
            method : 'GET',
            url : 'healthsystems'
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
    
    this.addHealthSystems = function addHealthSystems(healthsystem) {
        return $http({
            method : 'POST',
            url : 'healthsystem',
            data : healthsystem
        });
    }
}]);