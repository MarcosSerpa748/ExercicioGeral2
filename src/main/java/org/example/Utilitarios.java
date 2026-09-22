package org.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Utilitarios {

    private static Optional<User> gerarUser(){
        Scanner sc = new Scanner(System.in);

            System.out.print("Digite seu nome:");
            String name = sc.nextLine();
            System.out.print("Digite a sua idade:");
            try {

                Integer age = sc.nextInt();
                User usuario = new User(name,age);
                System.out.println("Usuário criado com sucesso!");
                return Optional.ofNullable(usuario);

            }catch (InputMismatchException e){

                System.out.println("Erro!Valor inválido!Criando usuário padrão...");

                return Optional.empty();
            }
    }

    private static User gerarUserDefault(){
        return new User();
    }

    public static void corpoProjeto() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        User user = gerarUser().orElseGet(() -> gerarUserDefault());

        String escolha;
        do {
            System.out.println(
                    "Boas vindas, "+user.getName()+"!"+"\n"+
                            "Digite 1 para pesquisar um produto específico pelo seu ID"+"\n"+
                            "Digite 2 para ver todos os produtos armazenados no sistema"+"\n"+
                            "Digite 0 para finalizar o sistema");

            escolha = sc.nextLine();

            switch (escolha){
                case "1" ->{

                    try {
                        user.pesquisarUmProduto();
                    }catch (IDInvalidoException e){
                        System.out.println(e.getMensagem());
                    }

                }
                case "2" ->{
                    System.out.println("*****PRODUTOS CADASTRADOS*****");
                    APIExterna.exibirTodosProdutos();
                }
                case "0" -> System.out.println("Finalizando o sistema...");
                default -> System.out.println("Escolha inexistente no sistema.");
            }
        }while (!escolha.equalsIgnoreCase("0"));


    }
}
