package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.dao.ObraDAO;
import org.example.leontisservlet.dao.ObraGuiaDAO;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.model.Obra;
import org.example.leontisservlet.model.ObraGuia;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui eu pego o id de quem sera excluido, excluo e mando para pagina de catalogo
@WebServlet(name = "excluirObra",value = "/excluir-obra")
public class ExcluirObra extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        int idObra;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idObra = Integer.parseInt(request.getParameter("id_obra"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Vendo se a obra nao esta em nenhum guia
            ObraGuiaDAO obraGuiaDAO = new ObraGuiaDAO();
            ResultSet rsObraGuia = obraGuiaDAO.buscarPorIdObra(idObra);
            LinkedList<ObraGuia> obrasGuia = MetodosAuxiliares.listarObraGuia(rsObraGuia);
            ObraDAO obraDAO = new ObraDAO();
            //Caso ela esteja em algum guia, nao deletamos e mostramos um erro na pagina de catalogo
            if(obrasGuia == null || !obrasGuia.isEmpty()){
                request.setAttribute("erro","Essa obra está em algum guia, exclua ele antes.");
            }else{
                //Deletando obra
                if(obraDAO.remover(idObra) <= 0){
                    request.setAttribute("erro","Erro ao excluir obra");
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
            }
            //Listando obras e enviando para tela de catalogo de obras
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
        }
    }
}
