package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.dao.ObraDAO;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.model.Obra;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

@WebServlet(name = "catalogoObra", value = "/obra")
public class CatalogoObra extends HttpServlet {
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

        //Pegando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ObraDAO obraDAO = new ObraDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu!=null){
            //Listando obras e enviando
            int id_museu = museu.getId();
            ResultSet rsObras = obraDAO.buscarPorIdMuseu(id_museu);
            LinkedList<Obra> obras = MetodosAuxiliares.listarObras(rsObras);
            //Vendo se nao deu algum erro
            if(obras != null){
                request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                request.setAttribute("obras",obras);
                request.setAttribute("museu",museu);

                request.getRequestDispatcher("gerencia/obra/obra.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
        }
    }

}
