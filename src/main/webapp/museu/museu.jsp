<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page import="org.example.leontisservlet.model.DiaFuncionamento" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../erros/paginaErro.jsp" %>

<%
    Museu museu = (Museu) request.getAttribute("museu");
    LinkedList<DiaFuncionamento> diasFuncionamento = (LinkedList<DiaFuncionamento>) request.getAttribute("dias_func");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/infoMuseu.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="png">
    <title><%=museu.getNmMuseu()%></title>
</head>
<body>
<jsp:include page="../navbar/navbarMuseu.jsp"></jsp:include>


<div class="assunto">
    <div id="museu">
        <div id="infoMuseu">
            <h1 id="nomeMuseu"><%=museu.getNmMuseu()%></h1>

            <p id="descMuseu">Descrição: <%=museu.getDescMuseu()%></p>

            <p id="rua">Rua: <%=museu.getRua()%></p>

            <p id="estadoCidade">Estado: <%=museu.getEstado()%>; Cidade: <%=museu.getCidade()%></p>

            <div id="diasFunc">
                <h2 id="titDiasFunc">Dias de Funcionamento</h2>
                <div class="dia">
                    <h3 class="subtit">Dias</h3>
                    <%
                        for (int i = 0; i < diasFuncionamento.size(); i++) {
                            String dia = diasFuncionamento.get(i).getDiaSemana();
                            if(dia.equals("dom")){
                    %>
                            <p>Domingo</p>
                        <%} else if (dia.equals("seg")) {%>
                            <p>Segunda</p>
                        <%} else if (dia.equals("ter")) {%>
                            <p>Terça</p>
                        <%} else if (dia.equals("qua")) {%>
                            <p>Quarta</p>
                        <%} else if (dia.equals("qui")) {%>
                            <p>Quinta</p>
                        <%} else if (dia.equals("sex")) {%>
                            <p>Sexta</p>
                        <%} else {%>
                            <p>Sábado</p>
                        <%}%>
                    <%}%>
                </div>
                <div class="abertura">
                    <h3 class="subtit">Abertura</h3>
                    <%
                        for (int i = 0; i < diasFuncionamento.size(); i++) {%>
                    <p><%=diasFuncionamento.get(i).getHrInicio()%></p>
                    <%}%>
                </div>
                <div class="fechamento">
                    <h3 class="subtit">Fechamento</h3>
                    <%
                        for (int i = 0; i < diasFuncionamento.size(); i++) {%>
                    <p><%=diasFuncionamento.get(i).getHrTermino()%></p>
                    <%}%>
                </div>
                <div class="valorIngresso">
                    <h3 class="subtit">Valor Ingresso</h3>
                    <%
                        for (int i = 0; i < diasFuncionamento.size(); i++) {%>
                    <p>R$<%=String.format("%.2f",diasFuncionamento.get(i).getPrDiaFuncionamento())%></p>
                    <%}%>
                </div>
                <p id="fechado">Outros dias: fechado</p>

            </div>
            <form action="editar-museu" method="post">
                <input type="text" name="id_museu_adm" id="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
                <input type="submit" value="Editar" id="editar">
            </form>
        </div>

        <img src="<%=museu.getUrlImagem()%>" id="fotoMuseu">
    </div>
</div>
</body>
</html>
