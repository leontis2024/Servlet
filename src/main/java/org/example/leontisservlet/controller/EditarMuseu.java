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

//Aqui eu mando ele para pagina de cadastro, porem com um objeto de museu como parametro
//para que ele saiba que deve editar
@WebServlet(name = "editarMuseu", value = "/editar-museu")
public class EditarMuseu extends HttpServlet {
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

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //<editor-fold desc="Pegando dias funcionamento">
            DiaFuncionamentoDAO diaFuncionamentoDAO = new DiaFuncionamentoDAO();
            ResultSet rs;
            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(),"seg");
            DiaFuncionamento segunda = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "ter");
            DiaFuncionamento terca = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "qua");
            DiaFuncionamento quarta = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "qui");
            DiaFuncionamento quinta = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "sex");
            DiaFuncionamento sexta = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "sab");
            DiaFuncionamento sabado = MetodosAuxiliares.pegarDiaFuncionamento(rs);

            rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(museu.getId(), "dom");
            DiaFuncionamento domingo = MetodosAuxiliares.pegarDiaFuncionamento(rs);
            //</editor-fold>

            //enviando atributos para tela de cadastro
            request.setAttribute("museu",museu);
            request.setAttribute("id_museu_adm",id_museu_adm);
            request.setAttribute("domingo",domingo);
            request.setAttribute("segunda",segunda);
            request.setAttribute("terca",terca);
            request.setAttribute("quarta",quarta);
            request.setAttribute("quinta", quinta);
            request.setAttribute("sexta", sexta);
            request.setAttribute("sabado", sabado);
            request.getRequestDispatcher("museu/cadastroMuseu.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
