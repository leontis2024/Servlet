package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.GuiaDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.dao.ObraDAO;
import org.example.leontisservlet.dao.ObraGuiaDAO;
import org.example.leontisservlet.model.Guia;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.model.Obra;
import org.example.leontisservlet.model.ObraGuia;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui enviamos ele para pagina de catalogo de obras com base no guia, permitindo tambem ele de editar
//as obras do guia
@WebServlet(name = "catalogoObrasGuia", value = "/gerencia-obras-guia")
public class CatalogoObrasGuia extends HttpServlet {
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

        //Buscando pelo museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Pegando o guia
            GuiaDAO guiaDAO = new GuiaDAO();
            ResultSet rsGuia = guiaDAO.buscarPorId(idGuia);
            Guia guia = MetodosAuxiliares.pegarGuia(rsGuia);
            if(guia != null){
                //Buscando obras do guia
                ObraGuiaDAO obraGuiaDAO = new ObraGuiaDAO();
                ResultSet rsObraGuia = obraGuiaDAO.buscarPorIdGuia(guia.getId());
                LinkedList<ObraGuia> obrasGuia = MetodosAuxiliares.listarObraGuia(rsObraGuia);
                //Vendo se nao deu algum erro
                if(obrasGuia != null) {

                    //Listando obras do catalogo do museu
                    ObraDAO obraDAO = new ObraDAO();
                    ResultSet rsObra = obraDAO.buscarPorIdMuseu(museu.getId());
                    LinkedList<Obra> obrasCatalogo = MetodosAuxiliares.listarObras(rsObra);

                    //enviando atributos e indo para tela de obras do guia
                    request.setAttribute("id_museu_adm", idAdm);
                    request.setAttribute("museu",museu);
                    request.setAttribute("guia",guia);
                    request.setAttribute("obrasGuia",obrasGuia);
                    request.setAttribute("obrasCatalogo",obrasCatalogo);
                    request.getRequestDispatcher("gerencia/guia/obrasGuia.jsp").forward(request,response);
                }
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }

        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }

    }
}
