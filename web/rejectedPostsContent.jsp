<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            src="https://kit.fontawesome.com/1b1fb57155.js"
            crossorigin="anonymous"
        ></script>

        <link rel="stylesheet" href="./styles/rejectedPostsContent.css">
        <title>Rejected Posts Content | FPT Blog</title>
    </head>
    <body>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->

        <header <c:if test="${loginStatus == 'logined'}">login-kind="logined"</c:if><c:if test="${loginStatus != 'logined'}">login-kind="no-login"</c:if>>
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
                                <a href="loadPendingPosts?postStatus=WFA"><button>Pending Post</button></a>
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
                                        <c:url var="loadCurrentProfileLink" value="loadProfile">
                                            <c:param name="email" value="${currentUser.email}" />
                                        </c:url>
                                        <a href="${loadCurrentProfileLink}"><p>Profile</p></a>
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
                    <a href="createPostPage">
                        <div class="container_item create-post">
                            <img src="./images/create-blog.svg" />
                            <p>Create Post</p>
                        </div>
                    </a>
                    <a href="loadRejectedPosts">
                        <div class="container_item create-post">
                            <img src="./images/post-management.png" />
                            <p>Rejected Posts</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="rejected-posts">
                <c:forEach var="post" items="${REJECTED_POSTSLIST}">
                    <div class="container-item">
                        <div class="rejected-reason">
                            <div class="reason-label">
                                <h1>Reason of rejecting</h1>
                            </div>
                            <div class="reason-main-item">
                                <div class="mentor-info">
                                    <div class="avt">
                                        <img src="${post.avatar}" alt="">
                                    </div>
                                    <a href="loadProfile?email=${post.emailApprover}">
                                        <p class="mentor-name">${post.namePost}</p>
                                    </a>
                                </div>
                                <div class="reason-content">
                                    ${post.note}
                                </div>
                            </div>
                        </div>
                        <div class="content-item">
                            <div class="title">
                                <div class="time">${post.approvedDate}</div>
                                <p class="title-text">${post.title}</p>
                                <div class="tag">
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
                            <div class="post-content">
                                ${post.postContent}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- footer -->
        <!-- footer -->
        <!-- footer -->
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
