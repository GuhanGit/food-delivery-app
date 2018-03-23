angular.module('app.services', [])
.service("GetClaimsSystem", ['$http', function($http) {
    this.getClaimSystemByName = function getClaimSystemByName(name) {
        var query = '?name='+name;
        return $http({
            method: 'GET',
            url: 'claimsystem' + query
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
    this.getAllClaims = function getAllClaims() {
        return $http({
            method : 'GET',
            url : 'claimsystems'
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            return response;
        });
    };
    
    this.addClaims = function addClaims(claim) {
        return $http({
            method : 'POST',
            url : 'claimsystem',
            data : claim
        });
    }
}]);