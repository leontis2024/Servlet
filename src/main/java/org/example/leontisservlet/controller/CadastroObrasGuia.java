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

/*
Aqui eu atualizo as obras do guia com base em uma das 3 interações feitas pelo usuario:
- Salvar guia e ir para tela de catalogo de guias
- Adicionar mais uma obra no final do guia (manda para mesma pagina, com uma obra a mais no final)
- Remover a ultima obra do guia (manda para mesma pagina, com uma obra a menos no final
*/

@WebServlet(name = "editarObrasGuia", value="/editar-obras-guia")
public class CadastroObrasGuia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando ids e quantidade de obras
        int id_museu_adm;
        int idGuia;
        int qtdObras; //Usada para sabermos quantas obras vao ter no guia ao inserir e na pagina
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idGuia = Integer.parseInt(request.getParameter("id_guia"));
            qtdObras = Integer.parseInt(request.getParameter("qtdObras"));
        }catch(Exception e){
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Buscando pelo museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Pegando o guia
            GuiaDAO guiaDAO = new GuiaDAO();
            ResultSet rsGuia = guiaDAO.buscarPorId(idGuia);
            Guia guia = MetodosAuxiliares.pegarGuia(rsGuia);
            if(guia != null){
                //Atualizando todos os registros
                ObraGuiaDAO obraGuiaDAO = new ObraGuiaDAO();
                for (int i = 0; i < qtdObras; i++) {
                    ObraGuia obraGuiaUpdate = new ObraGuia(i+1, guia.getId(), Integer.parseInt(request.getParameter("obra_"+i)), -1, request.getParameter("descLoc_"+i));
                    if(obraGuiaDAO.alterar(obraGuiaUpdate,guia.getId(),i+1) == 0){
                        obraGuiaDAO.inserir(obraGuiaUpdate);
                    }
                }
                //Tratando as 3 possiveis interações
                String modoEnvio = request.getParameter("envio");
                if(modoEnvio.equals("Salvar Alterações")){
                    //Apenas salvar as obras e voltar para pagina do catalogo

                    //Listando guias
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
                }else {
                    if (modoEnvio.equals("Adicionar Nova")) {
                        qtdObras++;
                    } else if (modoEnvio.equals("Excluir Última")) {
                        //Remover ultima obra do guia
                        obraGuiaDAO.remover(idGuia,qtdObras);
                        qtdObras--;
                    }else{
                        //Se nao for nenhum desses joga em erro
                        request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                        return;
                    }
                    //Buscando obras do guia inseridas
                    ResultSet rsObraGuia = obraGuiaDAO.buscarPorIdGuia(guia.getId());
                    LinkedList<ObraGuia> obrasGuia = MetodosAuxiliares.listarObraGuia(rsObraGuia);
                    //Vendo se nao deu algum erro
                    if(obrasGuia != null) {

                        //Listando obras do catalogo do museu
                        ObraDAO obraDAO = new ObraDAO();
                        ResultSet rsObra = obraDAO.buscarPorIdMuseu(museu.getId());
                        LinkedList<Obra> obrasCatalogo = MetodosAuxiliares.listarObras(rsObra);

                        //enviando atributos e voltando para pagina
                        request.setAttribute("id_museu_adm",id_museu_adm);
                        request.setAttribute("museu",museu);
                        request.setAttribute("guia",guia);
                        request.setAttribute("obrasGuia",obrasGuia);
                        request.setAttribute("obrasCatalogo",obrasCatalogo);
                        request.setAttribute("qtdObras", qtdObras);
                        request.getRequestDispatcher("gerencia/guia/obrasGuia.jsp").forward(request,response);
                    }
                }
            }
        }



    }
}
