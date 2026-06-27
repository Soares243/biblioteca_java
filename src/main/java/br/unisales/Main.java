package br.unisales;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unisales.menu.CategoriaMenu;
import br.unisales.menu.LivroMenu;
import br.unisales.menu.MultaMenu;
import br.unisales.menu.NotificacaoMenu;
import br.unisales.menu.RelatorioMenu;
import br.unisales.menu.ReservaMenu;
import br.unisales.menu.UsuarioMenu;
import br.unisales.menu.util.MenuUtil;
import br.unisales.manager_factory.ManagerFactory;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
        MenuUtil.configurarConsoleUtf8();
        configurarLogs();
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        exibirCabecalho();
        if (!inicializarBanco()) {
            scanner.close();
            return;
        }

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("escolha uma opcao: ");

            switch (opcao) {
                case 1 -> menuUsuario();
                case 2 -> menuLivro();
                case 3 -> menuAutor();
                case 4 -> menuCategoria();
                case 5 -> menuEmprestimo();
                case 6 -> menuReserva();
                case 7 -> menuMulta();
                case 8 -> menuRelatorio();
                case 9 -> menuNotificacao();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirCabecalho() {
        System.out.println("==========================================");
        System.out.println("       SISTEMA DE BIBLIOTECA");
        System.out.println("==========================================");
        System.out.println("Banco: PostgreSQL");
        System.out.println("Host : localhost:5432");
        System.out.println("Base : biblioteca_java");
        System.out.println("------------------------------------------");
    }

    private static boolean inicializarBanco() {
        System.out.println("Conectando ao banco e preparando tabelas...");
        try (ManagerFactory emf = new ManagerFactory()) {
            System.out.println("Banco conectado. Tabelas verificadas/criadas com update.");
            System.out.println();
            return true;
        } catch (RuntimeException e) {
            Throwable causa = encontrarCausaRaiz(e);
            System.out.println();
            System.out.println("Nao foi possivel conectar ao PostgreSQL.");
            System.out.println("Confira se o PostgreSQL esta aberto e se o banco biblioteca_java existe.");
            System.out.println("URL: jdbc:postgresql://localhost:5432/biblioteca_java");
            System.out.println("Usuario: postgres");
            System.out.println("Erro: " + causa.getMessage());
            return false;
        }
    }

    private static Throwable encontrarCausaRaiz(Throwable erro) {
        Throwable causa = erro;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        return causa;
    }

    private static void configurarLogs() {
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.SEVERE);
        hibernateLogger.setUseParentHandlers(false);

        Logger jbossLogger = Logger.getLogger("org.jboss");
        jbossLogger.setLevel(Level.SEVERE);
        jbossLogger.setUseParentHandlers(false);
    }

    private static void exibirMenu() {
        System.out.println("1 - Usuario");
        System.out.println("2 - Livro");
        System.out.println("3 - Autor");
        System.out.println("4 - Categoria");
        System.out.println("5 - Emprestimo");
        System.out.println("6 - Reserva");
        System.out.println("7 - Multa");
        System.out.println("8 - Relatorio");
        System.out.println("9 - Notificacoes");
        System.out.println("0 - Sair");
        System.out.println("-------------------------------------");
    }

    private static void menuCategoria() {
        MenuUtil.limparConsole();
        new CategoriaMenu(scanner);
    }

    private static void menuUsuario() {
        MenuUtil.limparConsole();
        new UsuarioMenu(scanner);
    }

    private static void menuLivro() {
        MenuUtil.limparConsole();
        new LivroMenu(scanner);
    }

    private static void menuAutor() {
        MenuUtil.limparConsole();
        new br.unisales.menu.AutorMenu(scanner);
    }

    private static void menuEmprestimo() {
        MenuUtil.limparConsole();
        new br.unisales.menu.EmprestimoMenu(scanner);
    }

    private static void menuReserva() {
        MenuUtil.limparConsole();
        new ReservaMenu(scanner);
    }

    private static void menuMulta() {
        MenuUtil.limparConsole();
        new MultaMenu(scanner);
    }

    private static void menuRelatorio() {
        MenuUtil.limparConsole();
        new RelatorioMenu(scanner);
    }

    private static void menuNotificacao() {
        MenuUtil.limparConsole();
        new NotificacaoMenu(scanner);
    }

    private static Integer lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }
}
