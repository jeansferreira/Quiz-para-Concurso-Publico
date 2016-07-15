<%@ page session="false" %>
<%@ taglib uri="/tags/pager-taglib"  prefix="pg"%>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="filtro"            type="java.lang.String"  scope="request" class="java.lang.String"/>
<font face=Helvetica size=-1>Páginas:
<pg:prev export="pageUrl">&nbsp;<a href="<%= pageUrl + filtro %>">[&lt;&lt; Anterior]</a></pg:prev>
<pg:pages><%
  if (pageNumber.intValue() < 10) {
    %>&nbsp;<%
  }
  if (pageNumber == currentPageNumber) {
    %><b><%= pageNumber %></b><%
  } else {
    %><a href="<%= pageUrl + filtro %>"><%= pageNumber %></a><%
  }
%>
</pg:pages>
<pg:next export="pageUrl">&nbsp;<a href="<%= pageUrl + filtro %>">[Próxima &gt;&gt;]</a></pg:next>
<br></font>
