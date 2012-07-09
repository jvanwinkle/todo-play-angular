function TodoCtrl($scope, $http) {
	
	
	$http(jsRoutes.controllers.Application.tasks()).success(function(data) {
			$scope.todos = data;
		});
	
	$scope.addTodo = function() {
		var newTodo = {
			id : null,
			text : $scope.todoText,
			done : false
		}
		
		$scope.todos.push(newTodo);
		$scope.todoText = '';
		var object = {"method" : jsRoutes.controllers.Application.newTask().method,
				      "url" : jsRoutes.controllers.Application.newTask().url,
				      "data" : newTodo};
		$http(object).success(function(data) {
			console.log("Save");
		});
	};

	$scope.remaining = function() {
		var count = 0;
		angular.forEach($scope.todos, function(todo) {
			count += todo.done ? 0 : 1;
		});
		return count;
	};
	
	$scope.update = function(todo) {
		var object = {"method" : jsRoutes.controllers.Application.updateTask().method,
			      "url" : jsRoutes.controllers.Application.updateTask().url,
			      "data" : todo};
		$http(object).success(function(data) {
			console.log("Update Successful");
		});
	};

	$scope.archive = function() {
		var oldTodos = $scope.todos;
		$scope.todos = [];
		angular.forEach(oldTodos, function(todo) {
			if (!todo.done) {
				$scope.todos.push(todo);
			} else {
				$http(jsRoutes.controllers.Application.deleteTask(todo.id)).success(function(){console.log("Delete Successful")});
			}
		});
	};
}