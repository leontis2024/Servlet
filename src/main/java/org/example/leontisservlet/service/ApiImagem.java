package org.example.leontisservlet.service;

import jakarta.servlet.http.Part;
import okhttp3.*;

import java.io.*;

public class ApiImagem {
    private static final OkHttpClient client = new OkHttpClient();

    public static String pegarUrlImagem(Part imagePart, String tabela, int id){
        try{
            //Convertendo para arquivo
            File fileImagem = converterPartParaFile(imagePart);
            String url_imagem = enviarParaApi(fileImagem,tabela,id+"");
            //Caso nao haja uma imagem, a api retorna um texto de erro, entao validamos ele aqui, vendo se
            //nao estamos trantando de uma imagem
            if(url_imagem.startsWith("http")){
                return url_imagem;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String enviarParaApi(File imageFile, String tableName, String id) throws IOException {
        //Definindo como imagem e criando o bodyFile
        MediaType mediaType = MediaType.parse("image/jpeg");
        RequestBody fileBody = RequestBody.create(mediaType, imageFile);

        //Criando o body do request, como um MultiPart e definindo os parametros
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(), fileBody)
                .addFormDataPart("nomeTabela", tableName)
                .addFormDataPart("id", id)
                .build();

        //Criando o request
        Request request = new Request.Builder()
                .url(System.getenv("IMG_API_ENDPOINT"))
                .post(requestBody)
                .build();

        //Fazendo o request retornando a string da url da imagem
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    public static File converterPartParaFile(Part filePart) throws IOException {
        //Pegando o nome do arquivo
        String fileName = filePart.getSubmittedFileName();

        //Criando um arquivo temporario, ja que o File obrigatoriamente deve estar no sistema
        File tempFile = File.createTempFile("upload_", "_" + fileName);

        //Guardando o conteudo do arquivo
        try (InputStream inputStream = filePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(tempFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        //Retornando o arquivo
        return tempFile;
    }

}
