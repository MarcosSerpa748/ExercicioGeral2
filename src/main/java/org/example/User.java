package org.example;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class User{
    private Integer id;
    private String name;
    private Integer age;

    public User(String name,Integer age){
        this.id = gerarID();
        this.name = name;
        this.age = age;
    }
    public User(){
        this.id = 0;
        this.name = "Visitante";
        this.age = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "ID:"+this.id+"\n"+
                "Name:"+this.name+"\n"+
                "Age:"+this.age+"\n";
    }

    private Integer gerarID(){
        Random random = new Random();
        return random.nextInt(50);
    }
    public void pesquisarUmProduto() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do produto desejado:");
        String id = sc.nextLine();

        Optional<Product> produtoPesquisado = APIExterna.pegarUmproduto(id);

        Product produtoDesemcapsulado = produtoPesquisado.orElseThrow(() -> new IDInvalidoException("Erro! Não existe nenhum produto com o ID "+id));

        System.out.println(
                "ID:"+produtoDesemcapsulado.id()+"\n"+
                "Nome do produto:"+produtoDesemcapsulado.title()+"\n"+
                "Preço:R$"+produtoDesemcapsulado.price()+"\n"+
                "Categoria:"+produtoDesemcapsulado.category()
        );
    }
}
