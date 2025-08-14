 angular.module('alunoApp', [])
      .controller('AlunoController', function($http) {
        var vm = this;
        vm.alunos = [];

      // listar alunos
      vm.listarAlunos = function() {
          $http.get('http://localhost:8080/api/alunos')
          .then(function(response) {
          vm.alunos = response.data;
          console.log(response.data);
        }, function() {
          alert('Erro ao carregar alunos');
        });
    };

      //salvar alunos
     vm.salvarAluno = function() {
      if(vm.edicao) {
        $http.put('http://localhost:8080/api/alunos/' + vm.novoAluno.id, vm.novoAluno)
          .then(function() {
            vm.listarAlunos();
            vm.novoAluno = {};
            vm.edicao = false;
          }, function() {
            alert('Erro ao atualizar aluno');
          });
      } else {
        $http.post('http://localhost:8080/api/alunos', vm.novoAluno)
          .then(function() {
            vm.listarAlunos();
            vm.novoAluno = {};
          }, function() {
            alert('Erro ao salvar aluno');
          });
      }
    };



      //editar aluno
      vm.editarAluno = function(aluno) {
      vm.novoAluno = angular.copy(aluno);
      vm.edicao = true;
    };

      //remover aluno
       vm.excluirAluno = function(id) {
      if(confirm('Confirma exclusão?')) {
        $http.delete('http://localhost:8080/api/alunos/' + id)
          .then(function() {
            vm.listarAlunos();
          }, function() {
            alert('Erro ao excluir aluno');
          });
      }
    };

       vm.listarAlunos();
});


    