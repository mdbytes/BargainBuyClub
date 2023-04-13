<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 4/13/23
  Time: 8:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- include navbar and meta data, page sources and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>

<div class="container-fluid contact get-started">
    <div class="container contact-container">
        <div class="row">
            <div class="col-lg-6">
                <div class="cta-info w-100">
                    <h3 class="display-4">Next steps?</h3>
                    <p style="color: black">
                        We look forward to designing a custom solution to fit your needs.
                        Satisfaction guaranteed.
                    </p>
                    <ul class="cta-info__list">
                        <li>
                            <i class="fa-solid fa-circle-check"></i>Send us a message today.
                        </li>
                        <li>
                            <i class="fa-solid fa-circle-check"></i>Give us an idea of your
                            strategic objectives.
                        </li>
                        <li>
                            <i class="fa-solid fa-circle-check"></i>Tell us about your business
                            needs.
                        </li>
                    </ul>
                    <p style="color: black">
                        We will contact you within 24 hours with a range of alternatives.
                    </p>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="contact-form">
                    <form id="form">
                        <h3 class="display-4">Contact Us</h3>
                        <div class="form-group">
                            <label id="name-label" for="name">Name</label><input name="from_name" type="text"
                                                                                 class="form-control" id="name"
                                                                                 placeholder="Enter full name"
                                                                                 aria-describedby="nameHelp"/><small
                                id="nameHelp" class="form-text text-muted">We'll never
                            share your name with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label id="email-label" for="email">Email address</label><input name="reply_to" type="email"
                                                                                            class="form-control"
                                                                                            id="email"
                                                                                            placeholder="e.g. fred@flintstones.com"
                                                                                            aria-describedby="emailHelp"
                                                                                            required=""/><small
                                id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                            else.</small>
                        </div>
                        <div class="mb-3">
                            <label for="comments">Message</label><textarea name="message" class="form-control"
                                                                           id="comments" placeholder="Your message here"
                                                                           required=""></textarea>
                        </div>
                        <button id="submit-button" type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Submit
                        </button>
                        <input id="submit" type="submit" class="btn btn-primary" disabled="" hidden/>
                    </form>
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Confirm Send</h5>
                                    <button id="close-modal" type="button" class="close" data-bs-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    Click <i>'Send'</i> to finalize your message. Click <i>'Cancel'</i> to cancel
                                    sending this message.
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                        Cancel
                                    </button>
                                    <button id="sendButton" type="button" class="btn btn-warning">Send</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="success-message"></div>
                    <div id="error-message"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>
