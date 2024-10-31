package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.leontisservlet.dao.GuiaDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Guia;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.ApiImagem;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

/*
Aqui pegamos todos os parametros da pagina de cadastro de guia e:
adicionamos um novo guia no banco ou atualizamos um já existente
com base no id recebido
Depois enviamos o usuario para tela de catalogo de guias
 */
@MultipartConfig
@WebServlet(name = "cadastroGuia", value = "/cadastro-guia")
public class CadastroGuia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //<editor-fold desc="Parametros">
        String stIdGuia = request.getParameter("id_guia");
        String tituloGuia = request.getParameter("nomeGuia");
        String descricao = request.getParameter("descricao");
        Part imagePart = request.getPart("image");

        //Validando e transformando parametros
        int idGuia;
        try {
            idGuia = Integer.parseInt(stIdGuia);
        }catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("erro","Valor inválido inserido no cadastro.");
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }
        //</editor-fold>

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //A url_imagem é vazia por agora, pois iremos altera-la depois de inserir
            Guia guiaInsert = new Guia(idGuia,museu.getId(),tituloGuia,descricao,"");
            GuiaDAO guiaDAO = new GuiaDAO();
            //Caso seja maior que 0, devemos alterar um guia ja existente
            if(guiaInsert.getId() > 0){
                //caso de erro ao alterar, enviamos ele para pagina de erro
                if(guiaDAO.alterar(guiaInsert,guiaInsert.getId()) <= 0){
                    request.setAttribute("erro", "Erro interno ao alterar guia, tente de novo mais tarde.");
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
            }else{
                //caso de erro ao inserir, enviamos ele para pagina de erro
                if(guiaDAO.inserir(guiaInsert) <= 0){
                    request.setAttribute("erro", "Erro interno ao inserir guia, tente de novo mais tarde.");
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
            }

            //Buscando guia inserido
            ResultSet rsGuia = guiaDAO.buscarPorTituloMuseu(museu.getId(),tituloGuia);
            Guia guia = MetodosAuxiliares.pegarGuia(rsGuia);
            if(guia != null){
                //Agora atualizamos a imagem, com base no novo id
                String url_imagem = ApiImagem.pegarUrlImagem(imagePart,"guia",guia.getId());
                if(url_imagem != null){
                    //Mudando a url no objeto e atualizando no banco
                    guia.setUrlImagem(url_imagem);
                    guiaDAO.alterarUrlImagem(guia.getUrlImagem(),guia.getId());
                }

                //Listando guias e enviando do catalogo e enviando ele para pagina de catalogo de guias
                ResultSet rsGuias = guiaDAO.buscarPorIdMuseu(museu.getId());
                LinkedList<Guia> guias = MetodosAuxiliares.listarGuias(rsGuias);
                //Vendo se nao deu algum erro
                if(guias != null){
                    request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                    request.setAttribute("guias",guias);
                    request.setAttribute("museu",museu);
                    request.getRequestDispatcher("gerencia/guia/guia.jsp").forward(request,response);
                }else {
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                }
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
