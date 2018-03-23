angular.module('app.controllers', [])
.controller("claimCtrl", ['$scope', "GetClaimsSystem",
    function ($scope, GetClaimsSystem) {
        $scope.getAllClaims = function () {
        	GetClaimsSystem.getAllClaims().then(function(data) {
                $scope.claimsystems = data;
            });
        };

        $scope.getClaimSystemByName = function () {        	
        	GetClaimsSystem.getClaimSystemByName($scope.name).then(function(data) {
                $scope.claimsystems = data;
            });
        }
        
        $scope.addClaims = function () {
        	GetClaimsSystem.addClaims($scope.claimSystem).then(function(data) {
                $scope.message = "" +
                		"Claim Added Successfully";
                $scope.claimList.reset();
            });
        }
        $scope.IsAdd = true;
        $scope.IsSearch = false;
        $scope.ShowAdd = function () {
        	
            //If DIV is hidden it will be visible and vice versa.
           // $scope.IsAdd = $scope.IsAdd ? false : true;
            
            $scope.IsAdd = false;
            $scope.IsSearch = true;
            //$scope.IsSearch = $scope.IsSearch ? true : false;
        }
        
        $scope.ShowSearch = function () {
        	
            //If DIV is hidden it will be visible and vice versa.
           // $scope.IsAdd = $scope.IsAdd ? false : true;
           // $scope.IsSearch = $scope.IsSearch ? true : false;
            
            $scope.IsSearch = false;
            $scope.IsAdd = true;
        }
        
    }
]);