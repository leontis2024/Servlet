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

//Aqui eu mando ele para pagina de cadastro, porem com um objeto de guia como parametro
//para que ele saiba que deve editar
@WebServlet(name = "editarGuia", value = "/editar-guia")
public class EditarGuia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        int idGuia;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idGuia = Integer.parseInt(request.getParameter("id_guia"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Pegando guia
            GuiaDAO guiaDAO = new GuiaDAO();
            ResultSet rsGuia = guiaDAO.buscarPorId(idGuia);
            Guia guia = MetodosAuxiliares.pegarGuia(rsGuia);
            if(guia != null){
                //enviando atributos para tela de cadastro
                request.setAttribute("id_museu_adm", idAdm);
                request.setAttribute("guia", guia);
                request.setAttribute("museu", museu);
                request.getRequestDispatcher("gerencia/guia/cadastroGuia.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
