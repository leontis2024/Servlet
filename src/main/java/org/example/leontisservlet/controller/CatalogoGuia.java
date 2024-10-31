package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.GuiaDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Guia;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui enviamos ele para pagina de catalogo de guia, com todos os guias do museu
@WebServlet(name = "catalogoGuia" , value = "/guia")
public class CatalogoGuia extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Pegando museu
        MuseuDAO museuDAO = new MuseuDAO();
        GuiaDAO guiaDAO = new GuiaDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu != null){
            //Listando guias do museu e enviando
            int id_museu = museu.getId();
            ResultSet rsGuias = guiaDAO.buscarPorIdMuseu(id_museu);
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
        }else {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
