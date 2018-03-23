angular.module('app.controllers', [])
.controller("myCtrl", ['$scope', "GetHealthSystem",
    function ($scope, GetHealthSystem) {
        $scope.getAllHealthSystems = function () {
        	GetHealthSystem.getAllHealthSystems().then(function(data) {
                $scope.healthsystems = data;
            });
        };

        $scope.getHealthSystemByName = function () {        	
        	GetHealthSystem.getHealthSystemByName($scope.name).then(function(data) {
                $scope.healthsystems = data;
            });
        }
        
        $scope.addHealthSystems = function () {
        	GetHealthSystem.addHealthSystems($scope.healthSystem).then(function(data) {
                $scope.message = "Successfully added";
                $scope.healthSystem.reset();
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