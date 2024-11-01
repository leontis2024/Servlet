package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.leontisservlet.dao.*;
import org.example.leontisservlet.model.*;
import org.example.leontisservlet.service.ApiImagem;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

/*
Aqui pegamos todos os parametros da pagina de cadastro de artista e:
adicionamos um novo artista no banco ou atualizamos um já existente
com base no id recebido
Depois enviamos o usuario para tela de informações do artista inserido ou editado
 */
@MultipartConfig
@WebServlet(name = "cadastroObra", value = "/cadastro-obra")
public class CadastroObra extends HttpServlet {

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
        String stIdObra = request.getParameter("id_obra");
        String nome = request.getParameter("nomeObra");
        String descricao = request.getParameter("descricao");
        String stAnoInicio = request.getParameter("ano_inicio");
        String stAnoFim = request.getParameter("ano_termino");
        String stIdGenero = request.getParameter("generoObra");
        String stIdArtista = request.getParameter("artistaObra");
        Part imagePart = request.getPart("image");

        //Validando e transformando parametros
        int idArtista;
        int idObra;
        int anoInicio;
        int anoFim;
        int idGenero;
        try{
            idArtista = Integer.parseInt(stIdArtista);
            idObra = stIdObra.isEmpty()?-1:Integer.parseInt(stIdObra);
            anoInicio = Integer.parseInt(stAnoInicio);
            anoFim = Integer.parseInt(stAnoFim);
            idGenero = Integer.parseInt(stIdGenero);
        }catch (NumberFormatException e) {
            request.setAttribute("erro","Valor inválido inserido no cadastro.");
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }
        //</editor-fold>

        //Pegando artista da obra e validando
        ArtistaDAO artistaDAO = new ArtistaDAO();
        ResultSet rsArtista = artistaDAO.buscarPorId(idArtista);
        Artista artistaObra = MetodosAuxiliares.pegarArtista(rsArtista);
        if(artistaObra == null) {
            request.setAttribute("erro", "Não foi encontrado o artista dessa obra, tente de novo mais tarde.");
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Pegando genero da obra e validando
        GeneroDAO generoDAO = new GeneroDAO();
        ResultSet rsGenero = generoDAO.buscarPorId(idGenero);
        Genero generoObra = MetodosAuxiliares.pegarGenero(rsGenero);
        if(generoObra == null) {
            request.setAttribute("erro", "Não foi encontrado o genero dessa obra, tente de novo mais tarde.");
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }


        //Buscando pelo museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu != null){
            //A url_imagem é vazia por agora, pois iremos altera-la depois de inserir
            Obra obraInsert = new Obra(idObra,anoInicio,anoFim,descricao,nome,idGenero,idArtista,museu.getId(),"");
            if(anoFim >= anoInicio){
                ObraDAO obraDAO = new ObraDAO();
                if(obraInsert.getId() > 0) {
                    //caso de erro ao alterar, enviamos ele para pagina de erro
                    if(obraDAO.alterar(obraInsert,obraInsert.getId()) <= 0){
                        request.setAttribute("erro", "Erro interno ao alterar obra, tente de novo mais tarde.");
                        request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                        return;
                    }
                }else {
                    //caso de erro ao inserir, enviamos ele para pagina de erro
                    if(obraDAO.inserir(obraInsert) <= 0){
                        request.setAttribute("erro", "Erro interno ao inserir obra, tente de novo mais tarde.");
                        request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                        return;
                    }
                }
                //Buscando obra inserida
                ResultSet rsObras = obraDAO.buscarPorNome(obraInsert.getNmObra());
                Obra obra = MetodosAuxiliares.pegarObra(rsObras);
                if(obra != null){
                    //Agora atualizamos a imagem, com base no novo id
                    String urlImagem = ApiImagem.pegarUrlImagem(imagePart,"obra",obra.getId());
                    if(urlImagem != null && !urlImagem.isEmpty()){
                        //Mudando a url no objeto e atualizando no banco
                        obra.setUrlImagem(urlImagem);
                        obraDAO.alterarUrlImagem(obra.getUrlImagem(),obra.getId());
                    }

                    //enviando os atributos e indo para tela de informações da obra inserida
                    request.setAttribute("artista",artistaObra);
                    request.setAttribute("obra",obra);
                    request.setAttribute("genero",generoObra);
                    request.setAttribute("id_museu_adm",idAdm);
                    request.setAttribute("museu",museu);
                    request.getRequestDispatcher("gerencia/obra/obraInfo.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                }
            }else{
                //Listando artistas do catalogo
                ResultSet rsArtistas = artistaDAO.buscarTodos();
                LinkedList<Artista> artistas = MetodosAuxiliares.listarArtistas(rsArtistas);

                //Listando generos do catalogo
                ResultSet rsGeneros = generoDAO.buscarTodos();
                LinkedList<Genero> generos = MetodosAuxiliares.listarGeneros(rsGeneros);

                //Reenviando para pagina de cadastro com um erro
                request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                request.setAttribute("museu",museu);
                request.setAttribute("artistas",artistas);
                request.setAttribute("generos",generos);
                request.setAttribute("artista",artistaObra);
                request.setAttribute("genero",generoObra);
                request.setAttribute("obra",obraInsert);
                request.setAttribute("erro","Insira um ano de termino maior ou igual ao ano de inicio.");
                request.getRequestDispatcher("gerencia/obra/cadastroObra.jsp").forward(request,response);
            }
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }



    }
}
