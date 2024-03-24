package br.com.jeli.todolist;

import br.com.jeli.todolist.model.usuario.Usuario;
import br.com.jeli.todolist.service.TarefaService;
import br.com.jeli.todolist.service.UsuarioService;


import java.util.Scanner;

public class ToDoApplication {
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n"); //Atributo para ler o teclado
    private static UsuarioService usuarioService = new UsuarioService(); //Service pra realizar operações de Usuario

    private static TarefaService tarefaService = new TarefaService();


    public static void main(String [] args) {
        System.out.println("Running...");
        int opcao = exibirMenu(); //Chama o método exibir menu que retorna um inteiro com a escolha do cliente
        while (opcao != 3) {
            switch (opcao) {
                case 1:
                    entrar();
                    break;
                case 2:
                    cadastrar();
                    break;
            }
            opcao = exibirMenu();   //Chamamos novamente o método para que o usuario possa novamente realizar as escolhas
        }
        System.out.println("Volte Logo"); //Caso escolha 3 (SAIR) encerramos a aplicação
        System.out.println("Closing...");

    }

    private static int exibirMenu() {
        String menu = """
                ----------BEM VINDO----------
                1 - ENTRAR
                2 - CADASTRAR
                3 - SAIR(ENCERRAR APLICAÇÃO)
                """;                                      //Menu para escolha
        System.out.println(menu);
        return scanner.nextInt();                        //Retorno da escolha do cliente
    }

    private static void entrar() {
        System.out.println("Digite seu id: ");
        String id = scanner.next();
        System.out.println("Digite sua senha: ");
        String senha = scanner.next();
        Boolean entrou = usuarioService.entrar(id, senha);
        menuDeConta(entrou);
        System.out.println("Pressione enter para voltar ao menu principal: ");
        scanner.next();
    }

    private static void menuDeConta(Boolean entrou) {
        System.out.println("***********Bem vindo " + usuarioService.getNomeSessaoAtual() + "***********");
        while(entrou) {
            Integer menuConta = exibirMenuDeConta();
            switch (menuConta) {
                case 1:
                    listarTarefas();
                    break;
                case 2:
                    marcarTarefa();
                    break;
                case 3:
                    atualizarTarefa();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    entrou = false;                              //Variavel que controla o loop alteramos para false
            }                                                   // Para voltar ao menu principal
        }
    }

    private static int exibirMenuDeConta() {
        String menuDeConta = """
                1 - Listar Todas Tarefas
                2 - Marcar Tarefa como feita
                3 - Atualizar Tarefa
                4 - Remover Tarefa
                5 - Sair
                """;
        System.out.println(menuDeConta);
        return scanner.nextInt();
    }

    private static void listarTarefas() {
        tarefaService.listar();
        scanner.next();
        System.out.println("Pressione enter para voltar ao menu de conta: ");
        scanner.next();
    }

    private static void marcarTarefa() {

    }

    private static void atualizarTarefa() {

    }

    private static void removerTarefa() {

    }





















    private static void cadastrar() {
        System.out.println("Digite o seu id (deve conter no máximo 5 caracteres): ");
        String id = scanner.next();
        System.out.println("Digite o seu nome: ");
        String nome = scanner.next();
        System.out.println("Digite a sua senha (deve conter no máximo 8 caracters): ");
        String senha = scanner.next();
        Usuario usuario = new Usuario(id, nome, senha);
        usuarioService.cadastrar(usuario);

        System.out.println("Usuario cadastrado com sucesso");
        System.out.println("Pressione enter para voltar ao menu");
        scanner.next();
    }
}
