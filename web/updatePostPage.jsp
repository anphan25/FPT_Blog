
<%@page import="swp.account.AccountDTO"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet"
            />
        <script
            src="https://kit.fontawesome.com/03ade0a214.js"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.tiny.cloud/1/v5o2pa741vownqney7esv5efwtt657k8j38w8k2womugtuyg/tinymce/5/tinymce.min.js"
            referrerpolicy="origin"
        ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js"></script>
        <link rel="stylesheet" href="./styles/createPostPage.css" />
        <title>Update Blog</title>
    </head>
    <body>
        <% AccountDTO user = (AccountDTO) session.getAttribute("CURRENT_USER");
        if (user == null) {
            response.sendRedirect("loadBlogs");
        } else { %>
        <c:set var="oldContent" value="${requestScope.OLD_CONTENT}"/>
        <c:set var="currentUser" value="${sessionScope.CURRENT_USER}"/>
        <header>
            <div class="container_header">
                <div class="container_left">
                    <div class="container_logo">
                        <a href="loadBlogs">
                            <img
                                src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                                />
                        </a>
                    </div>
                </div>
                <a href="loadProfile?email=${currentUser.email}">
                <div class="container_right">
                    <div class="icon_notification_container">
                        <img src="./images/close_button_icon.svg" />
                    </div>
                </div>
                </a>
            </div>
        </header>

        <!-- Main -->

        <section class="main">
            <div class="container_main">
                <div class="container_left"></div>
                <div class="container_middle">
                    <div class="absolute_edit">
                        <p>Update Post</p>
                    </div>
                    <form
                        action="updatePost"
                        method="POST"
                        >
                        <input type="hidden" name="postId" value="${oldContent.ID}" />
                        <div class="container_content">
                            <div class="textarea_title_container">
                                <p class="update-title">${oldContent.title}</p>
                            </div>
                            <div class="textarea_content_container">
                                <textarea id="content_area" name="newContent" >
                                    ${oldContent.postContent}
                                </textarea
                                >
                            </div>
                        </div>
                        <div class="container_button">
                            <button type="submit">Update</button>
                        </div>
                    </form>

                </div>
                <div class="container_info"></div>
            </div>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            tinymce.init({
                selector: "#content_area",
                setup: function (ed) {
                    ed.on("click", function (event) {
                        console.log(`Mouse X: ${event.clientX}, Mouse Y: ${event.clientY}`);
                        const ref = document.querySelector(".container_info");
                        const stringHtml = `<div style="position: sticky; top: ${50}vh" class="pseudo_info"><h3>Editor Basics</h3><li>We use TinyMCE.</li></div>`;
                        ref.innerHTML = stringHtml;
                    });
                },
                plugins: "autoresize",
                toolbar:
                        "formatselect | " +
                        "bold italic backcolor forecolor| alignleft aligncenter " +
                        "alignright alignjustify | bullist numlist outdent indent | " +
                        "removeformat | help | codesample",
                content_style:
                        "body { font-weight: 400; font-size:18px }" + "p { margin: 0",
                toolbar_mode: "floating",
                statusbar: false,
            });

//            const form = document.querySelector("form");
//            form.addEventListener("submit", (event) => {
//                event.preventDefault();
//            });
//
//            function getContent() {
//                let tags = "";
//                let tagJquery = $("#textarea_tags_container button");
//                console.log(tagJquery);
//                let title = document.getElementById("title_textarea").value;
//                if (title) {
//                    if (tagJquery.length == 0 || tagJquery.length > 4) {
//                        console.log(tagJquery.length == 0);
//                        if (tagJquery.length == 0) {
//                            swal(
//                                    "Have some tags for better",
//                                    "Tags help your post a lot in SEO",
//                                    "error",
//                                    {
//                                        button: "Ok",
//                                    }
//                            );
//                        } else {
//                            swal(
//                                    "Too much tags",
//                                    "Too much tags make your post over control",
//                                    "error",
//                                    {
//                                        button: "Ok",
//                                    }
//                            );
//                        }
//                        return;
//                    } else {
//                        $("#textarea_tags_container button").each(function () {
//                            let test = $(this)[0].innerHTML;
//                            tags += test + " ";
//                        });
//                        var myContent = tinymce.get("content_area").getContent();
//                        $("#tags").val(tags);
//                        form.submit();
//                    }
//                } else {
//                    swal("You miss your title", "", "error", {button: "Ok"});
//                    return;
//                }
//            }
        </script>
        
        <% }%>
    </body>
</html>
