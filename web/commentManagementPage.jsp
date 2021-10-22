<%-- Document : CommentManagementPage Created on : Oct 20, 2021, 9:24:23 AM
Author : ASUS --%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
                             prefix="c"%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="loginStatus" value="${sessionScope.LOGIN}" />
<c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
<c:set var="commentList" value="${requestScope.COMMENT_LIST}" />
<c:if test="${empty currentUser}">
    <c:redirect url="notFoundPage" />
</c:if>
<c:if test="${currentUser.role != 'A'}">
    <c:redirect url="notFoundPage" />
</c:if>
<!DOCTYPE html>
<html>
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
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="./styles/commentManagement.css" />
        <title>Comments Management</title>
    </head>
    <body>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->
        <header>
            <div class="container_header">
                <div class="container_left">
                    <div class="toggle_sidebar" onclick="toggleSidebarPhone()">
                        <img src="./images/toggle_sidebar_icon.svg" />
                    </div>
                    <div class="container_logo">
                        <a href="loadBlogs">
                            <img
                                src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                                />
                        </a>
                    </div>
                    <div class="dropdown_category">
                        <div class="container_category">
                            <p>Categories</p>
                        </div>
                        <div class="dropdown_category_content">
                            <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}">
                                <div class="dropdown_category_item">
                                    <a href="searchByCategory?categoryId=${cateDTO.ID}"
                                       >${cateDTO.name}</a
                                    >
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <form id="searchit" action="searchTitle">
                            <input
                                placeholder="Search..."
                                name="titleValue"
                                autocomplete="off"
                                />
                            <div class="container_icon" onclick="submit_form()">
                                <i class="fas fa-search"></i>
                            </div>
                        </form>
                    </div>
                </div>
                <c:if test="${loginStatus == 'logined'}">
                    <div class="container_right">
                        <c:if test="${currentUser.role == 'S'}">
                            <div class="container_button_register">
                                <a href="createPostPage"><button>Create Post</button></a>
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'M'}">
                            <div class="container_button_register">
                                <a href="loadPendingPosts?postStatus=WFA"
                                   ><button>Pending Post</button></a
                                >
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'A'}">
                            <div class="container_button_register">
                                <a href="createCategoryPage"
                                   ><button>Create Category</button></a
                                >
                            </div>
                        </c:if>
                        <div class="icon_notification_container">
                            <img src="./images/notification_icon.svg" />
                        </div>
                        <a href="messengerPage">
                            <div class="icon_notification_container">
                                <img src="./images/chat.svg" />
                            </div>
                        </a>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img src="${currentUser.avatar}" />
                            </div>
                            <div class="dropdown-content">
                                <div class="item-top">
                                    <a
                                        ><h2>${currentUser.name}</h2>
                                        <p>@${currentUser.name}</p></a
                                    >
                                </div>
                                <div style="padding: 0.5rem 0">
                                    <div class="item">
                                        <a href="loadProfile?email=${currentUser.email}"
                                           ><p>Profile</p></a
                                        >
                                    </div>
                                </div>
                                <div class="item-bottom">
                                    <a href="logout">Sign Out</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${loginStatus != 'logined'}">
                    <div class="container_right">
                        <div class="container_button_login">
                            <button><a href="firstLoginPage">Login</a></button>
                        </div>
                        <div class="container_button_register">
                            <button><a href="registerPage">Create account</a></button>
                        </div>
                    </div>
                </c:if>
            </div>
        </header>

        <!--main-->
        <section class="container">
            <div class="navigation_left">
                <div class="sidebar_navigation">
                    <h2 class="title_navigation">Menu</h2>
                    <a href="loadBlogs">
                        <div class="container_item">
                            <img src="./images/house_icon.svg" />
                            <p>Home</p>
                        </div>
                    </a>
                    <a href="createCategoryPage">
                        <div class="container_item">
                            <img src="./images/category_icon.svg" />
                            <p>Create Category</p>
                        </div>
                    </a>
                    <a href="loadUserList">
                        <div class="container_item create-post">
                            <img src="./images/user-list.svg" />
                            <p>User List</p>
                        </div>
                    </a>
                    <a href="">
                        <div class="container_item create-post">
                            <img src="./images/comment.png" />
                            <p>Comments Management</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="navigation_right">
                <div class="title">
                    <h1>Comments Management (${requestScope.TOTAL_COMMENTS})</h1>
                </div>
                <div class="comment-list">
                    <table>
                        <thead>
                            <tr>
                                <th>Avatar</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>At Post</th>
                                <th>Comment Date</th>
                                <th>Content of comment</th>
                                <th>Action</th>
                            </tr>
                        </thead>                      
                        <tbody>
                            <c:forEach var="cmt" items="${commentList}">
                                <tr id="cmt-${cmt.ID}">
                                    <td class="avt-td">
                                        <img
                                            src="${cmt.avatar}"
                                            />
                                    </td>
                                    <td class="username"><p>${cmt.name}</p></td>
                                    <td><p class="userMail">${cmt.emailComment}</p></td>
                                    <td class="postTitle"><p>${cmt.postName}</p></td>
                                    <td class="postDate"><p >${cmt.date}</p></td>
                                    <td class="userCmt"><p>${cmt.content}</p></td>
                                    <td>
                                        <button class="del-btn" id="${cmt.ID}">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pagingIndex">
                    <c:set var="activeIndex" value="${requestScope.CHECK_INDEX}"/>
                    <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="i">
                        <a class="${activeIndex == i ? "active": ""} indexPage" href="loadAllComments?index=${i}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Footer -->

        <footer>
            <div class="container_footer">
                <p>
                    <span class="text_footer_strong">DEV Community</span> - A constructive
                    and inclusive social network for software developers. With you every
                    step of your journey.
                </p>
                <div style="margin: 0.25rem 0"></div>
                <p>
                    Built on <span class="text_footer_strong">Forem</span> - the
                    <span class="text_footer_strong">open source</span> software that
                    powers DEV and other inclusive communities
                </p>
            </div>
            <div class="text_footer_container">
                <p class="text_footer">
                    Made with
                    <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
                    <span class="text_footer_strong">Ân, An, Đan, Nam, Phương</span> ©
                    2021
                </p>
                <img src="./images/forem_icon.svg" />
            </div>
        </footer>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function deleteCommentInUI(id) {
            let deleteCmt = document.querySelector("#cmt-" + id);
            deleteCmt.remove();
        }

        function deleteComment(id) {
            $.ajax({
                url: "DeleteCommentServlet",
                data: {
                    cmtID: id
                },
                type: "POST",
                success: function (response) {
                    deleteCommentInUI(id);
                    swal({
                        title: "Deleting Successfully !!!",
                        icon: "success",
                        button: "Ok",
}                       );
                },
                error: function (xhr) {
                    alert('Server internal error.');
                }
            });
        }
        $(document).on('click', '.del-btn', function (e) {
            e.preventDefault();
            deleteComment(e.target.id.length > 0 ? e.target.id : e.currentTarget.id);

        });
    </script>
</html>
