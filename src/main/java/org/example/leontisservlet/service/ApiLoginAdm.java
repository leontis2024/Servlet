package org.example.leontisservlet.service;

import okhttp3.*;

import java.io.IOException;

public class ApiLoginAdm {
    private final OkHttpClient client = new OkHttpClient();

    public String createUser(String email, String senha) throws IOException {
        //Preparando json
        String json = "{"
                + "\"email\":\"" + email + "\","
                + "\"senha\":\"" + senha + "\""
                + "}";

        //Criando o body do request
        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json; charset=utf-8"));

        //Criando o request
        Request request = new Request.Builder()
                .url(System.getenv("USER_API_ENDPOINT"))
                .post(body)
                .build();

        //Fazendo o request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na criação do usuário: " + response.body().string());
            }

            return response.body().string();
        }
    }
}
