package br.com.jeli.todolist;

import br.com.jeli.todolist.model.tarefa.Tarefa;
import br.com.jeli.todolist.model.urgencia.Categoria;
import br.com.jeli.todolist.model.urgencia.NivelUrgencia;
import br.com.jeli.todolist.model.usuario.Usuario;
import br.com.jeli.todolist.service.NivelUrgenciaService;
import br.com.jeli.todolist.service.TarefaService;
import br.com.jeli.todolist.service.UsuarioService;


import java.util.Scanner;

public class ToDoApplication {
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n"); //Atributo para ler o teclado
    private static UsuarioService usuarioService = new UsuarioService(); //Service pra realizar operações de Usuario

    private static TarefaService tarefaService = new TarefaService();
    private static NivelUrgenciaService nivelUrgenciaService = new NivelUrgenciaService();


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
                    criarTarefa();
                    break;
                case 3:
                    marcarTarefa();
                    break;
                case 4:
                    atualizarNomeTarefa();
                    break;
                case 5:
                    atualizarUrgenciaTarefa();
                    break;
                case 6:
                    removerTarefa();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    entrou = false;                              //Variavel que controla o loop alteramos para false
            }                                                   // Para voltar ao menu principal
        }
    }

    private static int exibirMenuDeConta() {
        String menuDeConta = """
                1 - Listar Todas Tarefas
                2 - Criar Tarefa
                3 - Marcar Tarefa como Feita
                4 - Atualizar Nome da Tarefa
                5 - Atualizar Urgencia da Tarefa
                6 - Remover Tarefa
                7 - Sair
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

    private static void criarTarefa() {
        System.out.println("Digite qual é sua tarefa: ");
        String texto = scanner.next();
        System.out.println("Digite a sua urgencia: 1) URGENTE  2) ASSIM QUE POSSÍVEL 3) ADIAVEL");
        Integer urgenciaInt = scanner.nextInt();
        NivelUrgencia urgencia = selecionarUrgencia(urgenciaInt);
        Usuario usuario = usuarioService.getUsuarioSessaoAtual();
        Tarefa tarefa = new Tarefa(usuario, texto, urgencia);
        nivelUrgenciaService.cadastrar(urgencia);
        tarefaService.criarTarefa(tarefa);

        System.out.println("Tarefa cadastrada com sucesso");
        System.out.println("Pressione enter para voltar ao menu");
        scanner.next();
    }

    private static void marcarTarefa() {
        System.out.println("Qual ID da tarefa que deseja marcar como feita? ");
        Long idTarefa = scanner.nextLong();
        tarefaService.marcarTarefa(idTarefa);

        System.out.println("Tarefa atualizada com Sucesso!!");
        System.out.println("Pressione enter para voltar ao menu de conta");
        scanner.next();

    }

    private static void atualizarNomeTarefa() {
        System.out.println("Qual id da tarefa que deseja atualizar? ");
        Long idTarefa = scanner.nextLong();
        System.out.println("Qual novo nome da sua tarefa? ");
        String nome = scanner.next();
        tarefaService.atualizarNomeTarefa(idTarefa, nome);

        System.out.println("Tarefa atualizada com sucesso!!");
        System.out.println("Pressione enter para voltar ao menu principal");
        scanner.next();
    }

    private static void atualizarUrgenciaTarefa() {
        System.out.println("Digite o id da tarefa que deseja atualizar: ");
        Long idTarefa = scanner.nextLong();
        System.out.println("Digite o id da  Urgencia qe deseja inserir: 2)URGENTE 1)ASSIM QUE POSSIVEL 3)ADIAVEL");
        Long idUrgencia = scanner.nextLong();
        NivelUrgencia urgencia = nivelUrgenciaService.buscarPorId(idUrgencia);
        tarefaService.atualizarUrgencia(idTarefa, urgencia);
    }

    private static void removerTarefa() {
        System.out.println("Digite o id da tarefa que você deseja remover: ");
        Long idTarefa = scanner.nextLong();
        tarefaService.removerTarefa(idTarefa);


        System.out.println("Tarefa removida com sucesso!!");
        System.out.println("Pressione enter para voltar ao menu");
        scanner.next();

    }

    private static NivelUrgencia selecionarUrgencia(Integer urgenciaInt) {
        NivelUrgencia urgencia = null;
        if (urgenciaInt.equals(1)) {
            urgencia = new NivelUrgencia(Categoria.URGENTE);
        }

        if(urgenciaInt.equals(2)) {
            urgencia = new NivelUrgencia(Categoria.ASSIM_QUE_POSSIVEL);
        }

        if(urgenciaInt.equals(3)) {
            urgencia = new NivelUrgencia(Categoria.ADIAVEL);
        }

        return urgencia;
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


    }
}
