package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.leontisservlet.dao.ArtistaDAO;
import org.example.leontisservlet.dao.ArtistaGeneroDAO;
import org.example.leontisservlet.dao.GeneroDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Artista;
import org.example.leontisservlet.model.ArtistaGenero;
import org.example.leontisservlet.model.Genero;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.ApiImagem;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

/*
Aqui pegamos todos os parametros da pagina de cadastro de artista e:
adicionamos um novo artista no banco ou atualizamos um já existente
com base no id recebido
Depois enviamos o usuario para tela de informações do artista inserido ou editado
 */
@MultipartConfig
@WebServlet(name = "cadastroArtista", value = "/cadastro-artista")
public class CadastroArtista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }
        //<editor-fold desc="Parametros">
        String stIdArtista = request.getParameter("id_artista");
        String nome = request.getParameter("nomeObra");
        String descricao = request.getParameter("descricao");
        String stNascimento = request.getParameter("nascimento");
        String stFalecimento = request.getParameter("falecimento");
        String localNascimento = request.getParameter("local_nascimento");
        String localMorte = request.getParameter("local_morte");
        String[] stIdsGenerosArtista = request.getParameterValues("generoArtista");
        Part imagePart = request.getPart("image");

        //Validando e transformando parametros
        int idArtista;
        Date dtNascimento;
        Date dtFalecimento;
        int[] idsGenerosArtista = new int[stIdsGenerosArtista.length];
        try{
            //Caso nenhum id tenha sido recebido, definimos ele como -1, ou seja, devemos adicionar um artista
            idArtista = stIdArtista.isEmpty()?-1:Integer.parseInt(stIdArtista);
            //Caso alguma data esteja vazia, definimos ela como null
            dtNascimento = stNascimento.isEmpty()?null:Date.valueOf(stNascimento);
            dtFalecimento = stFalecimento.isEmpty()?null:Date.valueOf(stFalecimento);
            for (int i = 0; i < stIdsGenerosArtista.length; i++) {
                idsGenerosArtista[i] = Integer.parseInt(stIdsGenerosArtista[i]);
            }
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("erro","Valor inválido inserido no cadastro.");
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }


        //</editor-fold>

        //<editor-fold desc="Listando os generos do artista recebidos como parametro">
        GeneroDAO generoDAO = new GeneroDAO();
        LinkedList<Genero> generosArtista = new LinkedList<>();
        for (int i = 0; i < idsGenerosArtista.length; i++) {
            ResultSet rs = generoDAO.buscarPorId(idsGenerosArtista[i]);
            Genero genero = MetodosAuxiliares.pegarGenero(rs);
            if(genero != null){
                generosArtista.add(genero);
            }
        }
        //</editor-fold>

        //Buscando pelo museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu != null){
            //Criando artista
            //A url_imagem é vazia por agora, pois iremos altera-la depois de inserir
            Artista artistaInsert = new Artista(idArtista,nome,dtNascimento,dtFalecimento,localNascimento,localMorte,descricao,"");
            //Validando data de falecimento (maior que nascimento ou nula)
            if(dtFalecimento == null || dtFalecimento.compareTo(dtNascimento) > 0){
                ArtistaDAO artistaDAO = new ArtistaDAO();
                //Caso seja maior que 0, devemos alterar um artista ja existente
                if(artistaInsert.getId() > 0){
                    //caso de erro ao alterar, enviamos ele para pagina de erro
                    if(artistaDAO.alterar(artistaInsert,artistaInsert.getId()) <= 0){
                        request.setAttribute("erro", "Erro interno ao alterar artista, tente de novo mais tarde.");
                        request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                        return;
                    }
                }else{
                    //caso de erro ao inserir, enviamos ele para pagina de erro
                    if(artistaDAO.inserir(artistaInsert) <= 0){
                        request.setAttribute("erro", "Erro interno ao alterar artista, tente de novo mais tarde.");
                        request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                        return;
                    }
                }

                //Buscando artista inserido
                ResultSet rsArtista = artistaDAO.buscarPorNome(artistaInsert.getNmArtista());
                Artista artista = MetodosAuxiliares.pegarArtista(rsArtista);
                //Validando se não é nulo
                if(artista != null){
                    //Agora atualizamos a imagem, com base no novo id
                    String urlImagem = ApiImagem.pegarUrlImagem(imagePart,"artista",artista.getId());
                    if(urlImagem != null){
                        //Mudando a url no objeto e atualizando no banco
                        artista.setUrlImagem(urlImagem);
                        artistaDAO.alterarUrlImagem(artista.getUrlImagem(),artista.getId());
                    }
                    //Inserindo generos do artista
                    ArtistaGeneroDAO artistaGeneroDAO = new ArtistaGeneroDAO();
                    artistaGeneroDAO.removerTodosArtista(artista.getId());
                    for (int i = 0; i < generosArtista.size(); i++) {
                        artistaGeneroDAO.inserir(new ArtistaGenero(-1, artista.getId(), generosArtista.get(i).getId()));
                    }

                    //enviando os atributos e indo para tela de informações do artista inserido
                    request.setAttribute("artista",artista);
                    request.setAttribute("id_museu_adm",idAdm);
                    request.setAttribute("museu",museu);
                    request.getRequestDispatcher("gerencia/artista/artistaInfo.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                }
            }else{
                //Listando generos do catalogo
                ResultSet rsGenero = generoDAO.buscarTodos();
                LinkedList<Genero> generos = MetodosAuxiliares.listarGeneros(rsGenero);
                if(generos == null){
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
                //Reenviando para pagina de cadastro com um erro
                request.setAttribute("id_museu_adm",idAdm);
                request.setAttribute("museu",museu);
                request.setAttribute("generos",generos);
                request.setAttribute("artista",artistaInsert);
                request.setAttribute("generosArtista",generosArtista);
                request.setAttribute("erro","Insira uma data de falecimento maior do que a de nascimento");
                request.getRequestDispatcher("gerencia/artista/cadastroArtista.jsp").forward(request,response);
            }

        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }

    }
}
