<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 4/19/23
  Time: 6:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String errorMessage = "";
    if (request.getAttribute("errormessage") != null) {
        errorMessage = (String) request.getAttribute("errormessage");
        request.setAttribute("errormessage", null);
    }
%>

<div id="error-message" style="visibility: hidden;"><%= errorMessage %>
</div>

<!-- Button trigger modal -->
<button id="launch-error-button" type="button" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#errorModal"
        style="visibility: hidden">
    Launch error modal
</button>

<!-- Modal -->
<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="errorModalLabel">Oops! Sorry!</h1>
                <button id="close-modal-btn" type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <%= errorMessage %>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

