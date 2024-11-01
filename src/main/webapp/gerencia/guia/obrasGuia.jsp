<%@ page import="org.example.leontisservlet.model.Guia" %>
<%@ page import="org.example.leontisservlet.model.Obra" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.ObraGuia" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%
    Guia guia = (Guia) request.getAttribute("guia");
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");
    //obrasGuia armazena todas as obras do guia ja adicionadas
    LinkedList<ObraGuia> obrasGuia = (LinkedList<ObraGuia>) request.getAttribute("obrasGuia");
    //obrasCatalogo armazena todas as obras daquele museu
    LinkedList<Obra> obrasCatalogo = (LinkedList<Obra>) request.getAttribute("obrasCatalogo");
    Object stQtdObras = request.getAttribute("qtdObras");

    int qtdObras = stQtdObras != null?(int) stQtdObras:obrasGuia.size();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/obrasGuia.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="png">
    <title>Informações da Obra</title>
</head>
<body>
<jsp:include page="../../navbarMuseu.jsp"></jsp:include>


<h1 id="titulo">Gerenciar Obras do Guia</h1>

<form action="editar-obras-guia" method="post">
    <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
    <input type="text" name="id_guia" value="<%=guia.getId()%>" style="display: none">
    <input type="text" name="qtdObras" value="<%=qtdObras%>" style="display: none">
    <div id="obras">
<%--        Para cada obra do guia, mostramos uma div ao usuario, para que ele possa edita-la--%>
        <%for(int i = 0; i < qtdObras; i++){%>
        <div class="obra">
            <h2 class="numObra">Obra <%=i+1%></h2>

            <div class="selectObra">

                <label for="opcoesObras">Selecione a obra</label>
                <%--Setando autofocus no ultimo elemento --%>
                <select name="obra_<%=i%>" class="opcoesObras" id="opcoesObras" <%=i==qtdObras-1?"autofocus":""%>>

                    <%String txtDescLoc = "";
                    //Aqui listamos todas as obras do catalogo, para ele conseguir editar e escolher uma
                        for(int j = 0; j < obrasCatalogo.size(); j++){
                        if(i<obrasGuia.size()){
                            txtDescLoc = obrasGuia.get(i).getDesc_localizacao()!=null?obrasGuia.get(i).getDesc_localizacao():"";
                    %>
    <%--                        A segunda comparacao ve se a obra é a mesma que a selecionada antes e caso positivo, deixa como selected--%>
                            <option value="<%=obrasCatalogo.get(j).getId()%>" <%=obrasCatalogo.get(j).getId() == obrasGuia.get(i).getIdObra()?"selected":""%>>
                                <%=obrasCatalogo.get(j).getNmObra()%>
                            </option>
                        <%  }else{%>
                            <option value="<%=obrasCatalogo.get(j).getId()%>">
                                <%=obrasCatalogo.get(j).getNmObra()%>
                            </option>
                    <%  }
                    }%>
                </select>
            </div>
            <div class="descCaminhoObra">
                <label for="caminhoObra">Descreva o caminho para chegar à obra</label>
                <textarea name="descLoc_<%=i%>" id="caminhoObra" class="caminhoObra"><%=txtDescLoc%></textarea>
            </div>
        </div>
        <%}%>
    </div>

    <div id="botoes">
        <input type="submit" name="envio" class="botao" value="Adicionar Nova">
        <input type="submit" name="envio" class="botao" value="Excluir Última">
    </div>

    <div id="salvar">
        <input type="submit" name="envio" class="botao" value="Salvar Alterações">
    </div>
</form>
</body>
</html>