<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@page
    contentType="text/html" pageEncoding="UTF-8" %>
    <c:set var="loginStatus" value="${sessionScope.LOGIN}" />
    <c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
    <c:set var="postList" value="${requestScope.POST_LIST}"/>
    <c:if test="${empty currentUser}">
        <c:redirect url="notFoundPage" />
    </c:if>
    <c:if test="${currentUser.role != 'S'}">
        <c:redirect url="notFoundPage" />
    </c:if>
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

            <link rel="stylesheet" href="./styles/postsManagement.css" />
            <title>Posts Management | FPT Blog</title>
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
                                        <c:url var="cateLink" value="searchByCategory">
                                            <c:param name="categoryId" value="${cateDTO.ID}" />
                                        </c:url>
                                        <a href="${cateLink}">${cateDTO.name}</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="container_searchBar">
                            <form id="searchit" action="searchTitle">
                                <input placeholder="Search..." name="titleValue" />
                                <div class="container_icon" onclick="submit_form()">
                                    <i class="fas fa-search"></i>
                                </div>
                            </form>
                        </div>
                        <script>
                            function submit_form() {
                                var form = document.getElementById("searchit");
                                form.submit();
                            }
                        </script>
                    </div>

                    <div class="container_right">
                        <div class="container_button_register">
                            <button>
                                <a href="loadPendingPosts?postStatus=WFA">Pending Post</a>
                            </button>
                        </div>
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
                </div>
            </header>

            <section class="main">
                <div class="container">
                    <div class="row">
                        <div class="navigation_left">
                            <div class="sidebar_navigation">
                                <h2 class="title_navigation">Menu</h2>
                                <a href="loadBlogs">
                                    <div class="container_item">
                                        <img src="./images/house_icon.svg" />
                                        <p>Home</p>
                                    </div>
                                </a>
                                <a href="createPostPage">
                                    <div class="container_item create-post">
                                        <img src="./images/create-blog.svg" />
                                        <p>Create Post</p>
                                    </div>
                                </a>
                                <a href="postsManagementPage">
                                    <div class="container_item create-post">
                                        <img src="./images/post-management.png" />
                                        <p>Posts Management</p>
                                    </div>
                                </a>

                            </div>
                        </div>
                        <div class="navigation_right">
                            <div class="title">
                                <h1>Posts Management</h1>
                            </div>
                            <div class="posts">
                                <c:if test="${not empty postList}">
                                    <c:forEach var="post" items="${postList}">
                                        <div class="post-info">
                                            <div class="reject-by-label">
                                                <h1>Rejected Post</h1>
                                            </div>
                                            <div class="post-info-item">
                                                <div class="time">
                                                    ${post.approvedDate}
                                                </div>
                                                <a href="">
                                                    <p class="post-title">${post.title}</p>
                                                </a>
                                                <div class="post-tags">
                                                    <c:forEach var="tag" items="${post.tag}">
                                                        <c:url var="searchByTagLink" value="searchByTag">
                                                            <c:param name="tag" value="${tag}" />
                                                        </c:url>
                                                        <a href="${searchByTagLink}">
                                                            <p class="post-tag">#${tag}</p>
                                                        </a>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
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
    </html>