<%@ page import="com.utbm.lo54.bean.CourseSession" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Jiale
  Date: 2021/5/30
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>byDate</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<%--    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<%--    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-NU/T4JKmgovMiPaK2GP9Y+TVBQxiaiYFJB6igFtfExinKlzVruIK6XtKqxCGXwCG" crossorigin="anonymous"></script>--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-olOxEXxDwd20BlATUibkEnjPN3sVq2YWmYOnsMYutq7X8YcUdD6y/1I+f+ZOq/47" crossorigin="anonymous">--%>


    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">

</head>
<body>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="http://localhost:8099/dateandlocation">bydateandlocation</a></li>
    <li role="presentation"><a href="http://localhost:8099/dateindex">bydate</a></li>
    <li role="presentation"><a href="http://localhost:8099/motcle">mot cle</a></li>
</ul>
<!-- Single button -->
<div id="search">
    <div id="search-wrap">
        <select class="search-choise" id="selectbox">
            <option>dateandlocation</option>
        </select>
        <input class="search-content" type="text" placeholder="enter date" name="date" id="date">
        <input class="search-content" type="text" placeholder="enter location" name="location" id="location">
        <button class="search-btn" type="submit" onclick="search()">search</button>
    </div>
</div>

<table data-toggle="table" id="ttable">
    <tr>
        <th data-field="id">ID</th>
        <th data-field="code">Code</th>
        <th data-field="title">Titre</th>
        <th data-field="city">City</th>
        <th data-field="start">StartTime</th>
        <th data-field="end">EndTime</th>
        <th data-field="number">Current Number</th>
        <th data-field="action">Action</th>
    </tr>
    <%
        List<CourseSession> list=(List)session.getAttribute("courselists");
        for(int i=0;i<list.size();i++){
            CourseSession courseSession=list.get(i);
    %>
    <tr>
        <td><%=courseSession.getId() %></td>
        <td><%=courseSession.getCourse().getCode() %></td>
        <td><%=courseSession.getCourse().getTitle() %></td>
        <td><%=courseSession.getLocation().getCity() %></td>
        <td><%=courseSession.getStartDate() %></td>
        <td><%=courseSession.getEndDate() %></td>
        <td><%=courseSession.getCurrentNumber()%> /
                <%=courseSession.getMax()%>
        <td>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#exampleModal">
                inscrire
            </button>

            <!-- Modal -->

        </td>
    </tr>
    <%
        }
    %>

</table>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">S'inscrire</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="forminscrire" role="form"  action="/inscrire">
                    <div class="form-group">
                        <label for="name" class="col-form-label">Nom:</label>
                        <input type="text" id="name"  class="form-control" name="name" />
<%--                        <input type="text" class="form-control" id="recipient-nom">--%>
                    </div>
                    <div class="form-group">
                        <label for="prenom" class="col-form-label">Prenom:</label>
                        <input type="text" class="form-control" id="prenom" name="prenom">
                    </div>
                    <div class="form-group">
                        <label for="adresse" class="col-form-label">Adresse:</label>
                        <input type="text" class="form-control" id="adresse" name="adresse">
                    </div>
                    <div class="form-group">
                        <label for="telephone" class="col-form-label">Telephone:</label>
                        <input type="text" class="form-control" id="telephone" name="telephone">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" >S'inscrire</button>
                    </div>
                </form>
            </div>


        </div>
    </div>
</div>



</body>
<script >




    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var td_content = button.parents("tr").children("td");  //获取当前行中的所有td值
        var sessionid = td_content.eq(0).text();
        var modal = $(this)
        modal.find('.modal-title').text("S'inscrire")
        modal.find('.modal-body input').val(recipient)
        modal.find('.modal-body #coursesessionid').val(sessionid)
    });
    function search() {
        var options =document.getElementById("selectbox").value;
        var date=document.getElementById("date").value;
        var location=document.getElementById("location").value;
        var URLa='http://localhost:8099/bydateandlocation?date='+date+'&location='+location;

        // http://localhost:8099/bydateandlocation?date=2021-05-08 18:15:00&location=Belfort
        $.ajax({
            type:'get',
            // async:false,
            url: 'http://localhost:8099/byDateAndLocation?date='+date+'&location='+location,
            dataType: 'json',
            success: function(data){
                window.open(URLa)
            },
            error: function () {
                alert("error")

            }
        })

    }

    function inscrire() {
        $.ajax({
            type: 'post',
            url: 'http://localhost:8099/inscrire',
            dataType: 'json',
            data: $('#forminscrire').serialize(),
            success: function (data) {
                alert(data)
            },
            error: function () {
                alert(data)
            }
        })
    }



</script>
</html>
