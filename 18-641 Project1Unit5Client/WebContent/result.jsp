<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList, model.Automobile, model.OptionSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
Here is your automobile:<p>
<%
Automobile automobile = (Automobile)request.getAttribute("automobile");
%>
<table border="1" style="width:50%">
    <tr><td><%=automobile.getMake() + automobile.getModel() %></td>
        <td>Base Price</td>
        <td>$<%=automobile.getBaseprice() %></td>
    </tr>
<%
for(OptionSet optionSet : automobile.getOpset()) {
%>
    <tr>
        <td><%=optionSet.getName() %></td>
        <td><%=automobile.getOptionChoice(optionSet.getName())%></td>
        <td>$<%=automobile.getOptionChoicePrice(optionSet.getName())%></td>
    </tr>
<%
}
%>
    <tr><td>Total Cost</td><td></td><td>$<%=automobile.getTotalPrice() %></td></tr>
</table>

</body>
</html>