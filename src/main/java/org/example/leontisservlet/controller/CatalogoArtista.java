package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.*;
import org.example.leontisservlet.model.*;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui enviamos ele para pagina de catalogo de artista, com todos os artistas do sistema
@WebServlet(name = "catalogoArtista",value = "/artista")
public class CatalogoArtista extends HttpServlet {
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

        //Pegando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu != null){
            //Pegando artistas e enviando
            ArtistaDAO artistaDAO = new ArtistaDAO();
            ResultSet rsArtistas = artistaDAO.buscarTodos();
            LinkedList<Artista> artistas;
            artistas = MetodosAuxiliares.listarArtistas(rsArtistas);
            //Vendo se nao deu algum erro
            if(artistas != null){
                request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                request.setAttribute("museu",museu);
                request.setAttribute("artistas",artistas);
                request.setAttribute("museu",museu);
                request.getRequestDispatcher("gerencia/artista/artista.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }

        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }


    }


}
