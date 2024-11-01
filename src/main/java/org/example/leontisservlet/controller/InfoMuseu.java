package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.DiaFuncionamentoDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.DiaFuncionamento;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui eu pego o id de quem será exibido e mostro a tela com as informações dele
@WebServlet(name = "InfoMuseu", value = "/museu")
public class InfoMuseu extends HttpServlet {
    @Override
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

        //Procurando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu != null){
            //Listando dias de funcionamento
            DiaFuncionamentoDAO diaFuncionamentoDAO = new DiaFuncionamentoDAO();
            ResultSet rsDiaFunc = diaFuncionamentoDAO.buscarPorIdMuseu(museu.getId());
            LinkedList<DiaFuncionamento> diasFuncionamento = MetodosAuxiliares.listarDiaFuncionamento(rsDiaFunc);
            if(diasFuncionamento == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }
            //Definindo atributos e enviando para tela de info
            request.setAttribute("id_museu_adm",museu.getId_adm_museu());
            request.setAttribute("dias_func", MetodosAuxiliares.ordenarDiasFuncionamento(diasFuncionamento));
            request.setAttribute("museu", museu);
            request.getRequestDispatcher("museu/museu.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
        }
    }
}
