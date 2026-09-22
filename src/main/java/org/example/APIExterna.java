package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class APIExterna {
    private final static Gson gson = new Gson();

    public static void exibirTodosProdutos() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products/"))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());


        Product[] listaProdutos = gson.fromJson(resposta.body(),Product[].class);

        List<Product> todosProdutoConvertida = Arrays.asList(listaProdutos);

        todosProdutoConvertida.forEach(e -> System.out.println("ID:"+e.id()+"|Nome:"+e.title()+"|Preço:R$"+e.price()+"|Categoria:"+e.category()+"\n"));

    }

    public static Optional<Product> pegarUmproduto(String id) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products/"+id))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());

        return Optional.ofNullable(gson.fromJson(resposta.body(),Product.class));
    }
}
